package com.prajnafoundation.volunteerdonorportal.models;
import java.math.BigDecimal;
import java.util.*;
import com.prajnafoundation.volunteerdonorportal.entities.DonationTile;
public class DonationTileResponseObj extends LogMessage {

    private List<DonationTileObj> donations = null;

    public DonationTileResponseObj(List<DonationTile> donations, String logMessage){
        super(logMessage);
        this.donations = donations.stream().map(DonationTileObj::new).toList();
    }

    public DonationTileResponseObj( String logMessage){
        super(logMessage);
    }

    public List<DonationTileObj> getDonations() {
        return donations;
    }

    public void setDonations(List<DonationTileObj> donations) {
        this.donations = donations;
    }

    private static class DonationTileObj {
        Long donationTileId;
        String donationTitle;
        String description;
        BigDecimal targetAmount;
        Date startDate;
        Date endDate;
        boolean isActive;

        public DonationTileObj(DonationTile donationTile) {
            this.donationTileId = donationTile.getDonationTileId();
            this.donationTitle = donationTile.getDonationTitle();
            this.description = donationTile.getDescription();
            this.targetAmount = donationTile.getTargetAmount();
            this.startDate = donationTile.getStartDate();
            this.endDate = donationTile.getEndDate();
            this.isActive = donationTile.isActive();
        }


        public Long getDonationTileId() {
            return donationTileId;
        }

        public String getDonationTitle() {
            return donationTitle;
        }

        public String getDescription() {
            return description;
        }

        public BigDecimal getTargetAmount() {
            return targetAmount;
        }

        public Date getStartDate() {
            return startDate;
        }

        public Date getEndDate() {
            return endDate;
        }

        public boolean isActive() {
            return isActive;
        }
    }
}

