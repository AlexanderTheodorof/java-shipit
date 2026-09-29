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
            System.out.println("Посылка весит " + parcel.getWeight() +"кг.");
            System.out.println("В коробке осталось место только для " + (maxWeight - sumWeight) + "кг.");
        } else {
            parcels.add(parcel);
            sumWeight += parcel.getWeight();
            System.out.println("Посылка добавлена в корбку для посылок типа '" + getParcelsBoxType() + "'.");
        }
    }

    public ArrayList<T> getAllParcels() {
        return parcels; 
    }

    public int getMaxWeight() {
        return maxWeight; 
    }

    public int getNumberOfParcels(){
        return parcels.size(); 
    }

    public String getParcelsBoxType() {
        if (!parcels.isEmpty()) {
            return parcels.get(0).getType();
        } else {
            return "";
        }
        
    }
}
