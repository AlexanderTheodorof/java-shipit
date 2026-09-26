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
    public void packageItem() { // упаковать
        // packageItem — «упаковать». Для стандартных и скоропортящихся посылок этот метод должен просто выводить на экран текст Посылка <<XXX>> упакована, а для хрупких посылок — строку Посылка <<XXX>> обёрнута в защитную плёнку, а затем Посылка <<XXX>> упакована, где XXX — описание посылки.
        System.out.println("Посылка " + description + " обёрнута в защитную плёнку"); 
        super.packageItem();
    }

    @Override
    public String toString() {
        String objectDescript = super.toString() + "\n"
            + "Хрупкая посылка была обернута в защитную пленку.";
        return objectDescript;
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.println(type + " " + description + " изменила местоположение на " + newLocation);
    }
}
