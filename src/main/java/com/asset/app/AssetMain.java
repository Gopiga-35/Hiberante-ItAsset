package com.asset.app;

import com.asset.bean.Asset;
import com.asset.dao.AssetDAO;

public class AssetMain {

    public static void main(String[] args) {

        AssetDAO dao = new AssetDAO();

        System.out.println("----- IT Asset Management -----");

        try {

            
            Asset asset = new Asset();
            asset.setAssetTag("LAPTOP-020");          
            asset.setCategory("LAPTOP");
            asset.setModel("Dell Latitude 5440");
            asset.setSerialNo("DL-5440-020");         
            asset.setTotalQuantity(8);
            asset.setAvailableQuantity(8);
            asset.setStatus("ACTIVE");

            boolean inserted = dao.insertAsset(asset);
            System.out.println(inserted ? "ASSET ADDED" : "ADD FAILED");


            Asset existing = dao.findAsset("LAPTOP-020");

            if (existing != null && existing.getAvailableQuantity() > 0) {
                System.out.println("\nCHECKOUT SUCCESS");
            } else {
                System.out.println("\nASSET NOT AVAILABLE");
            }


            boolean deleted = dao.deleteAsset("LAPTOP-020");
            System.out.println(deleted ? "ASSET DELETED" : "DELETE FAILED");

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
