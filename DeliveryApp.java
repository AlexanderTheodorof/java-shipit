import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class DeliveryApp {

    private static final Scanner scanner          = new Scanner(System.in);
    private static List<Parcel> allParcels        = new ArrayList<>();
    private static List<Tracable> tracableParcels = new ArrayList<>();

    private static ParcelBox<StandardParcel>   standardParcelBox;
    private static ParcelBox<PerishableParcel> perishableParcelBox;
    private static ParcelBox<FragileParcel>    fragileParcelBox; 
    
    public static void main(String[] args) {
        boolean running = true;
        
        standardParcelBox   = new ParcelBox<>(15);
        perishableParcelBox = new ParcelBox<>(7);
        fragileParcelBox    = new ParcelBox<>(12);
        
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    printLocationAllTracableParcels();
                    break;
                case 5:
                    printBoxContent();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 —  Добавить посылку");
        System.out.println("2 —  Отправить все посылки");
        System.out.println("3 —  Посчитать стоимость доставки");
        System.out.println("4 -- Посмотреть рассположение всех отслеживаемых посылок");
        System.out.println("5 -- Вывести содержимое коробки");
        System.out.println("0 — Завершить");
    }

    // реализуйте методы ниже

    private static void addParcel() {
        // Подсказка: спросите тип посылки и необходимые поля, создайте объект и добавьте в allParcels
        String description;
        String deliveryAddress;
        int weight;
        int sendDay;
        System.out.println("Какую посылку вы хотите отправить?");
        System.out.println("1. Обычную");
        System.out.println("2. Хрупкую");
        System.out.println("3. Скоропортящуюуся");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
                case 1:
                    description = addParcelsUserInputString("Что вы хотите отправить?");
                    deliveryAddress = addParcelsUserInputString("Введите адрес получателя");
                    weight = addParcelsUserInputInt("Каков вес посылки? Введите целое положительное число: "); 
                    sendDay = addParcelsUserInputInt("Когда вы хотите отправить посылку? Введите день: ");
                    StandardParcel standardParcel = new StandardParcel(description, deliveryAddress, weight, sendDay); 
                    allParcels.add(standardParcel);
                    standardParcelBox.addParcel(standardParcel);
                    break;
                case 2:
                    description = addParcelsUserInputString("Что вы хотите отправить?");
                    deliveryAddress = addParcelsUserInputString("Введите адрес получателя");
                    weight = addParcelsUserInputInt("Каков вес посылки? Введите целое положительное число: "); 
                    sendDay = addParcelsUserInputInt("Когда вы хотите отправить посылку? Введите день: ");
                    FragileParcel fragileParcel = new FragileParcel(description, deliveryAddress, weight, sendDay);
                    allParcels.add(fragileParcel);
                    tracableParcels.add(fragileParcel);
                    fragileParcelBox.addParcel(fragileParcel);
                    break;
                case 3:
                    description     = addParcelsUserInputString("Что вы хотите отправить?");
                    deliveryAddress = addParcelsUserInputString("Введите адрес получателя");
                    weight          = addParcelsUserInputInt("Каков вес посылки? Введите целое положительное число: "); 
                    sendDay         = addParcelsUserInputInt("Когда вы хотите отправить посылку? Введите день: ");
                    int timeToLive  = addParcelsUserInputInt("Введите срок годности посылки в днях: ");
                    PerishableParcel perishableParcel = new PerishableParcel(description, deliveryAddress, weight, sendDay, timeToLive);
                    allParcels.add(perishableParcel);
                    perishableParcelBox.addParcel(perishableParcel);
                    break;
                default:
                    System.out.println("Ошибочная команда. Введите числа от 1 до 3.");
}
    }

    
    private static void sendParcels() {
        // Пройти по allParcels, вызвать packageItem() и deliver()
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        // Посчитать общую стоимость всех доставок и вывести на экран
        int sum = 0;
        for (Parcel parcel : allParcels) {
            sum += parcel.calculateDeliveryCost(); 
        }
        System.out.println("Общая стоимость доставки: " + sum);
    }

    private static void printLocationAllTracableParcels() {
        for (Tracable tracable : tracableParcels) {
            System.out.println("Введите новое местоположение отслеживаемой посылки");
            tracable.reportStatus(scanner.nextLine());
        }
    }

    private static void printBoxContent() {
        boolean runnig = true;
        while(runnig) {
            menuPrintBoxContent();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    boxContent(standardParcelBox);
                    break;
                case 2:
                    boxContent(fragileParcelBox);
                    break;
                case 3:
                    boxContent(perishableParcelBox);
                    break;
                case 0:
                    runnig = false;
                    break;
                default:
                    System.out.println("Невеная команда");
            }
        }
    }
    private static void menuPrintBoxContent() {
        System.out.println("Содержимое какой коробки вы хотите посмотреть?");
        System.out.println("1. Коробка со стандартными посылками");
        System.out.println("2. Коробка с хрупкими посылками");
        System.out.println("3. Коробка со скоропортящимися посылками");
        System.out.println("0. Выход");
    }
    private static <T extends Parcel> void boxContent(ParcelBox<T> parcelBox){
        int i = 0;
        for(T parcel : parcelBox.getAllParcels()) {
            System.out.println("Посылка №" + (i+1));
            System.out.println("=".repeat(20));
            System.out.println(parcel);
            System.out.println("=".repeat(20));
            i++; 
        }
    }
    
    private static String addParcelsUserInputString(String enterDataHello) {
        System.out.println(enterDataHello);
        return scanner.nextLine();
    }

    private static int addParcelsUserInputInt(String enterDataHello) {
        int positiveNumber = 0;
        System.out.print(enterDataHello);
        while(true) {
            positiveNumber = Integer.parseInt(scanner.nextLine());
            if (positiveNumber > 0) {
                return positiveNumber;
            } else {
                System.out.println("Введите положительное число");
            }
        }
    }
}
