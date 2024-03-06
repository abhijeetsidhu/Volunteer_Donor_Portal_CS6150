package com.prajnafoundation.volunteerdonorportal.services;

import com.prajnafoundation.volunteerdonorportal.entities.DonationTile;
import com.prajnafoundation.volunteerdonorportal.models.DonationTileResponseObj;
import com.prajnafoundation.volunteerdonorportal.models.LogMessage;
import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface DonationTileService {

    DonationTileResponseObj getAllDonationTiles();

    DonationTileResponseObj getDonationTileById(Long id);

    DonationTileResponseObj createDonationTile(String donationTitle, BigDecimal targetAmount, Date startDate, Date endDate,  boolean isActive);

    DonationTileResponseObj updateDonationTile(Long donationTileId, String donationTitle, BigDecimal targetAmount, Date startDate, Date endDate, Boolean isActive);

    DonationTileResponseObj deleteDonationTile(Long donationTileId);

}