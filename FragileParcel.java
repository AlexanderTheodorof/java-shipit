class FragileParcel extends Parcel implements Tracable {           // хрупкая посылка

    FragileParcel(String description,
                  String deliveryAddress,
                  int    weight,
                  int    sendDay) {
        super(description, deliveryAddress, weight, sendDay);
        super.type = "Хрупкая посылка";
    }

    @Override
    public int getDeliveryCost() {
        return super.getDeliveryCost() + 3; 
    }
    
    @Override
    public void packageItem() { 
        System.out.println("Посылка '" + description + "' обёрнута в защитную плёнку"); 
        super.packageItem();
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.println(type + " '" + description + "' изменила местоположение на '" + newLocation + "'");
    }
}
