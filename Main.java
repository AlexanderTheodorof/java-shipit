import java.util.ArrayList;
import java.util.Random;

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

        ArrayList<Tracable> tracebleParcels = new ArrayList<>();
        tracebleParcels.add(fragileParcel);
        tracebleParcels.add(fragileParcel);
        tracebleParcels.add(fragileParcel);
        tracebleParcels.add(fragileParcel);
        for (Tracable tracable : tracebleParcels) {
            tracable.reportStatus("петровско-разумовская");
        }

        // это работает
        // ParcelBox<StandardParcel> standardParcelBox = new ParcelBox<>(6);
        // standardParcelBox.addParcel(standartParcel);
        // standardParcelBox.addParcel(standartParcel);
        // standardParcelBox.addParcel(standartParcel);
        // standardParcelBox.addParcel(standartParcel);
        // for (StandardParcel stPr : standardParcelBox.getAllParcels()) {
        //     System.out.println(stPr);
        // }

        // это тоже работает
        // ParcelBox<PerishableParcel> perishableParcelBox = new ParcelBox<>(8);
        // perishableParcelBox.addParcel(perishableParcel);
        // perishableParcelBox.addParcel(perishableParcel);
        // perishableParcelBox.addParcel(perishableParcel);
        // for (PerishableParcel prPr : perishableParcelBox.getAllParcels()) {
        //     System.out.println(prPr);
        // }

        ParcelBox<FragileParcel> fragileParcelBox = new ParcelBox<>(7);
        fragileParcelBox.addParcel(fragileParcel);
        fragileParcelBox.addParcel(fragileParcel);
        fragileParcelBox.addParcel(fragileParcel);
        fragileParcelBox.addParcel(fragileParcel);

        for (FragileParcel frPr : fragileParcelBox.getAllParcels()) {
            System.out.println(frPr);
        }
        // for (Tracable tracebleParcel : tracebleParcels) {
        //     tracebleParcel.reportStatus("Петровско-Разумовская");
        // }
        // System.out.println(standartParcel);
        // System.out.println("~".repeat(20));
        // System.out.println(perishableParcel);
        // System.out.println("~".repeat(20));
        // System.out.println(fragileParcel);

        // System.out.println("_".repeat(20));
        // standartParcel.packageItem();
        // perishableParcel.packageItem();
        // fragileParcel.packageItem();

        // System.out.println(perishableParcel.isExpired(6));
        // System.out.println(perishableParcel.isExpired(15));

        // standartParcel.diliver();
        // perishableParcel.diliver();
        // fragileParcel.diliver();

        // fragileParcel.reportStatus("Петровско-разумовская");
    }
    // public static void main(String[] args) {
    //     Random random = new Random();
    //     String[] descriptStPc   = {"Варежки", 
    //                                "Шапка",
    //                                "Носки"};
        
    //     String[] descriptFrPc   = {"Хрустальная ваза",
    //                                "Инкунабула XV века",
    //                                "Библия Гутенберга",
    //                                "Первое издание Евгения Онегина",
    //                                "Скифская керамика"};
        
    //     String[] descriptPrPc   = {"Средиземноморские устрицы",
    //                                "Камчатский краб",
    //                                "Карельская форель",
    //                                "Тихоокенаские креветки"};
        
    //     String[] deliveryAdress = {"Москва-Петушки",
    //                                "Петровско-Раузмовская",
    //                                "Сумеру",
    //                                "Хермон",
    //                                "Кармель"};
    //     ParcelBox<StandardParcel> stParcelBox   = new ParcelBox<>(random.nextInt(15));
    //     ParcelBox<FragileParcel>  frParcelBox   = new ParcelBox<>(random.nextInt(5));
    //     ParcelBox<PerishableParcel> prParcelBox = new ParcelBox<>(random.nextInt(13));
    //     for (int i = 0; i < 3;i++) {
    //         int rndStParcelDescription = randon.nextInt(descriptStPc.size);
    //         int rndFrParcelDescription = random.nextInt(descriptFrPc.size);
    //         int rndPrParcelDescription = random.nextInt(descriptPrPc.size);
            
    //         String rndStParcelDescript = descriptStPc[rndStParcelDescription];
    //         String rndFrParcelDscript  = descriptFrPc[rndFrParcelDescription];
    //         String rndPrParcelDescript = descriptPrPc[rndPrParcelDescription];
            
    //         stParcelBox.addParcel(new StandardParcel(rndStParcelDescript, ));
    //         frParcelBox.addParcel(new FragileParcel(rndFrParcelDscript, ));
    //         prParcelBox.addParcel(new PerishableParcel(rndPrParcelDescript, ));
    //     }
    // }
}
