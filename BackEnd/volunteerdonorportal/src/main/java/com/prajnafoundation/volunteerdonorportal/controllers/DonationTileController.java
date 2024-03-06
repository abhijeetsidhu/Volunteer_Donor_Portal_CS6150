package com.prajnafoundation.volunteerdonorportal.controllers;
import com.prajnafoundation.volunteerdonorportal.models.DonationTileResponseObj;
import com.prajnafoundation.volunteerdonorportal.services.DonationTileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping("/donation")
public class DonationTileController {

    private final DonationTileService donationTileService;

    @Autowired
    public DonationTileController(DonationTileService donationTileService) {
        this.donationTileService = donationTileService;
    }

    @GetMapping
    public DonationTileResponseObj getDonationTiles(@RequestParam(name = "id", required = false) Long id) {
        if (id == null) {
            return donationTileService.getAllDonationTiles();
        } else {
            return donationTileService.getDonationTileById(id);
        }
    }

    @PostMapping
    public DonationTileResponseObj createDonationTile(@RequestParam String donationTitle,
                                                      @RequestParam BigDecimal targetAmount,
                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
                                                      @RequestParam(defaultValue = "true") boolean isActive) {
        return donationTileService.createDonationTile(donationTitle, targetAmount, startDate, endDate, isActive);
    }

    @PutMapping
    public DonationTileResponseObj updateDonationTile(@RequestParam Long donationTileId,
                                                      @RequestParam(required = false) String donationTitle,
                                                      @RequestParam(required = false) BigDecimal targetAmount,
                                                      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
                                                      @RequestParam(required = false) Boolean isActive) {
        return donationTileService.updateDonationTile(donationTileId, donationTitle, targetAmount, startDate, endDate, isActive);
    }

    @DeleteMapping
    public DonationTileResponseObj deleteDonationTile(@RequestParam Long donationTileId) {
        return donationTileService.deleteDonationTile(donationTileId);
    }
}
