package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SummarySalePerSellerDTO;
import com.devsuperior.dsmeta.projections.SaleReportProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.services.SaleService;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;

    @GetMapping(value = "/summary")
    public Page<SummarySalePerSellerDTO> search1(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate minDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate maxDate,
            /*Com base no desafio, usei o "required = false" para que tanto o maxDate, quanto o minDate possam se tornar opcional.*/
            Pageable pageable)
    {
            Page<SummarySalePerSellerDTO> result = service.search1(minDate, maxDate, pageable);

            return result;
    }


    @GetMapping(value = "/report")
    public Page<SaleReportDTO> search2(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate minDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate maxDate,
            String name,
            Pageable pageable)
    {
        Page<SaleReportDTO> result = service.search2(minDate, maxDate, name, pageable);

        return result;
    }

}