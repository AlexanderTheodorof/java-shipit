import java.util.ArrayList;

class ParcelBox <T extends Parcel> {
    private final ArrayList<T> parcels = new ArrayList<>();
    private final int maxWeight;
    private       int sumWeight;
    
    ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
        sumWeight      = 0;
    }

    public void addParcel(T parcel) {
        if (sumWeight + parcel.getWeight() > maxWeight) {
            System.out.println("Максимальный вес упаковки привышен. Добавте посылку в другую коробку.");
        } else {
            parcels.add(parcel);
            sumWeight += parcel.getWeight();
        }
    }

    public ArrayList<T> getAllParcels() {
        return parcels; 
    }
}
