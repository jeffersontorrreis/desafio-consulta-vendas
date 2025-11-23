package com.devsuperior.dsmeta.services;

import java.time.LocalDate;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SummarySalePerSellerDTO;
import com.devsuperior.dsmeta.projections.SaleReportProjection;
import com.devsuperior.dsmeta.projections.SummarySalePerSellerProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

    @Transactional(readOnly = true)
    public Page<SummarySalePerSellerDTO> search1(
            LocalDate minDate,
            LocalDate maxDate,
            Pageable pageable)
    {
        if(maxDate == null){
            maxDate = LocalDate.now();
        } /*Se a data final não for informada, considerar a data atual do sistema*/

        if(minDate == null){
            minDate  = maxDate.minusYears(1L);
        } /*Se a data inicial não for informada, considerar a data de 1 ano antes da data final.*/

        /*Considere que se ambos forem null, teremos maxDate como data atual, e minDate com um ano antes do maxDate.*/

         /*O "minusYears" está para contar exatamente um ano, porém o arquivo "import.sql" tem gaps nas datas, ou seja,
            não segue uma cronologia, logo os valores de vendas por vendedor pode alterar.*/

        Page<SummarySalePerSellerProjection> list = repository.search1(minDate, maxDate, pageable);
        return list.map(x->new SummarySalePerSellerDTO(x));
    }


    @Transactional(readOnly = true)
    public Page<SaleReportDTO> search2(
            LocalDate minDate,
            LocalDate maxDate,
            String name,
            Pageable pageable)
    {
        if(maxDate == null){
            maxDate = LocalDate.now();
        }

        if(minDate == null){
            minDate  = maxDate.minusYears(1L);
        }

        Page<SaleReportProjection> list = repository.search2(minDate, maxDate, name, pageable);
        return list.map(x->new SaleReportDTO(x));
    }
  }
  /*Usei o "required = false" no controller para depois usar a logica no service com os if's.*/
