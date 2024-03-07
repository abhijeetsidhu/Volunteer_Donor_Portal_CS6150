package com.prajnafoundation.volunteerdonorportal.services;

import com.prajnafoundation.volunteerdonorportal.entities.DonationTile;
import com.prajnafoundation.volunteerdonorportal.models.DonationTileResponseObj;
import com.prajnafoundation.volunteerdonorportal.repositories.DonationTileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DonationTileServiceImpl implements DonationTileService {

    private final DonationTileRepository donationTileRepository;

    @Autowired
    public DonationTileServiceImpl(DonationTileRepository donationTileRepository) {
        this.donationTileRepository = donationTileRepository;
    }

    /**
     * Retrieves a list of all donation tiles.
     *
     * @return A DonationTileResponseObj containing the list of donation tiles and a log message.
     */
    @Override
    public DonationTileResponseObj getAllDonationTiles() {
        List<DonationTile> donationTiles = donationTileRepository.findAll();
        String logMessage;
        if (donationTiles.isEmpty()) {
            logMessage = "No donation tiles exist!";
            return new DonationTileResponseObj(logMessage);
        }
        logMessage = "Successfully found " + donationTiles.size() + " donation tile(s)!";
        return new DonationTileResponseObj(donationTiles, logMessage);
    }

    /**
     * Retrieves a donation tile by its unique identifier (ID).
     *
     * @param id The unique identifier of a donation tile.
     * @return A DonationTileResponseObj containing the donation tile information and a log message.
     */
    @Override
    public DonationTileResponseObj getDonationTileById(Long id) {
        String logMessage;
        if (id == null) {
            logMessage = "No donation tile exist with id " + id + "!";
            return new DonationTileResponseObj(logMessage);
        }
        Optional<DonationTile> donationTileOptional = donationTileRepository.findById(id);

        List<DonationTile> donationTiles = donationTileOptional.map(Collections::singletonList).orElseGet(Collections::emptyList);

        if (donationTiles.isEmpty()) {
            logMessage = "No donation tile exist with id " + id + "!";
            return new DonationTileResponseObj(logMessage);
        }
        logMessage = "Successfully found the donation tile with Id: " + id;
        return new DonationTileResponseObj(donationTiles, logMessage);
    }

    @Override
    public DonationTileResponseObj createDonationTile(String donationTitle, BigDecimal targetAmount, Date startDate, Date endDate, boolean isActive) {
        DonationTile donationTile = new DonationTile();
        donationTile.setDonationTitle(donationTitle);
        donationTile.setTargetAmount(targetAmount);
        donationTile.setStartDate(startDate);
        donationTile.setEndDate(endDate);
        donationTile.setActive(isActive);

        donationTileRepository.save(donationTile);

        String logMessage = "Donation tile for " + donationTitle + " has been successfully created!";
        return new DonationTileResponseObj(logMessage);
    }

    @Override
    public DonationTileResponseObj updateDonationTile(Long donationTileId, String donationTitle, BigDecimal targetAmount, Date startDate, Date endDate, Boolean isActive) {
        Optional<DonationTile> donationTileOptional = donationTileRepository.findById(donationTileId);
        StringBuilder logMessage;

        if (donationTileOptional.isEmpty()) {
            logMessage = new StringBuilder("Donation tile with ID " + donationTileId + " not found.");
            return new DonationTileResponseObj(logMessage.toString());
        }

        DonationTile donationTile = donationTileOptional.get();
        logMessage = new StringBuilder("Donation tile with ID " + donationTileId + " has been updated. Updated fields: ");

        if (donationTitle != null) {
            donationTile.setDonationTitle(donationTitle);
            logMessage.append("donationTitle, ");
        }
        if (targetAmount != null) {
            donationTile.setTargetAmount(targetAmount);
            logMessage.append("targetAmount, ");
        }
        if (startDate != null) {
            donationTile.setStartDate(startDate);
            logMessage.append("startDate, ");
        }
        if (endDate != null) {
            donationTile.setEndDate(endDate);
            logMessage.append("endDate, ");
        }
        if(isActive != null) {
            donationTile.setActive(isActive);
            logMessage.append("isActive, ");
        }

        donationTileRepository.save(donationTile);

        if (logMessage.toString().endsWith(", ")) {
            logMessage.delete(logMessage.length() - 2, logMessage.length());
        } else {
            logMessage = new StringBuilder("Nothing updated for donation tile with ID " + donationTileId);
        }
        return new DonationTileResponseObj(logMessage.toString());
    }

    @Override
    public DonationTileResponseObj deleteDonationTile(Long donationTileId) {
        Optional<DonationTile> donationTileOptional = donationTileRepository.findById(donationTileId);

        if (donationTileOptional.isEmpty()) {
            String logMessage = "Donation tile with ID " + donationTileId + " not found.";
            return new DonationTileResponseObj(logMessage);
        }

        donationTileRepository.deleteById(donationTileId);

        String logMessage = "Donation tile with ID " + donationTileId + " has been successfully deleted.";
        return new DonationTileResponseObj(logMessage);
    }
}
