class Main {
    public static void main(String[] args) {
        String descript1        = "Книга Божественная комедия";
        String descript2        = "Бабушкины пирожки";
        String descript3        = "Хрустальная ваза";
        String deliveryAddress  = "Улица Пушкина, дом Колотушкина";
        int weight1            = 2;
        int weight2            = 3;
        int weight3            = 5;
        int sendDay1           = 7;
        int sendDay2           = 4;
        int sendDay3           = 5;
        int timeToLive         = 10;

        StandardParcel   standartParcel   = new StandardParcel  (descript1, deliveryAddress, weight1, sendDay1            );
        PerishableParcel perishableParcel = new PerishableParcel(descript2, deliveryAddress, weight2, sendDay2, timeToLive);
        FragileParcel    fragileParcel    = new FragileParcel   (descript3, deliveryAddress, weight3, sendDay3            );
        
        System.out.println(standartParcel);
        System.out.println("~".repeat(20));
        System.out.println(perishableParcel);
        System.out.println("~".repeat(20));
        System.out.println(fragileParcel);

        System.out.println("_".repeat(20));
        standartParcel.packageItem();
        perishableParcel.packageItem();
        fragileParcel.packageItem();

        System.out.println(perishableParcel.isExpired(6));
        System.out.println(perishableParcel.isExpired(15));

        standartParcel.diliver();
        perishableParcel.diliver();
        fragileParcel.diliver();
    }
}
