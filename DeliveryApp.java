import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class DeliveryApp {

    private static final Scanner        scanner         = new Scanner(System.in);
    private static       List<Parcel>   allParcels      = new ArrayList<>();
    private static       List<Tracable> tracableParcels = new ArrayList<>();

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
            int choice = Integer.parseInt(scanner.nextLine().strip());

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
                    showLocationAllTracableParcels();
                    break;
                case 5:
                    showBoxContent();
                    break;
                case 0:
                    System.out.println("Хорошего дня. Посылки будут доставлены в срок 0/");
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("=".repeat(20));
        System.out.println("Выберите действие:");
        System.out.println("1 —  Добавить посылку");
        System.out.println("2 —  Отправить все посылки");
        System.out.println("3 —  Посчитать стоимость доставки");
        System.out.println("4 -- Посмотреть рассположение всех отслеживаемых посылок");
        System.out.println("5 -- Показать содержимое коробки");
        System.out.println("0 —  Завершить");
        System.out.println("=".repeat(20));
    }

    // реализуйте методы ниже

    private static void addParcel() {
        // Подсказка: спросите тип посылки и необходимые поля, создайте объект и добавьте в allParcels
        boolean running = true;
        
        String description     = addParcelsUserInputString("Описание посылки: ");
        String deliveryAddress = addParcelsUserInputString("Введите адрес получателя:");
        int weight             = addParcelsUserInputInt("Каков вес посылки? Введите целое положительное число: "); 
        int sendDay            = addParcelsUserInputInt("Когда вы хотите отправить посылку? Введите день: ");
        
        while(running) {
            System.out.println("#".repeat(20));
            System.out.println("Какой тип посылки вы хотите отправить?");
            System.out.println("1. Обычную");
            System.out.println("2. Хрупкую");
            System.out.println("3. Скоропортящуюся");
            System.out.println("4. Выход");
            int choice = Integer.parseInt(scanner.nextLine().strip());
            switch (choice) {
                case 1:
                    System.out.println("_".repeat(20));
                    StandardParcel standardParcel = new StandardParcel(description, deliveryAddress, weight, sendDay); 
                    allParcels.add(standardParcel);
                    standardParcelBox.addParcel(standardParcel);
                    System.out.println("Стадартная посылка принята");
                    System.out.println("_".repeat(20));
                    running = false;
                    break;
                case 2:
                    FragileParcel fragileParcel = new FragileParcel(description, deliveryAddress, weight, sendDay);
                    System.out.println("_".repeat(20));
                    allParcels.add(fragileParcel);
                    tracableParcels.add(fragileParcel);
                    fragileParcelBox.addParcel(fragileParcel);
                    System.out.println("Хрупкая посылка принята");
                    System.out.println("_".repeat(20));
                    running = false;
                    break;
                case 3:
                    System.out.println("_".repeat(20));
                    int timeToLive  = addParcelsUserInputInt("Введите срок годности посылки в днях: ");
                    PerishableParcel perishableParcel = new PerishableParcel(description, deliveryAddress, weight, sendDay, timeToLive);
                    allParcels.add(perishableParcel);
                    perishableParcelBox.addParcel(perishableParcel);
                    System.out.println("Скоропортящаяся посылка приянта");
                    System.out.println("_".repeat(20));
                    running = false;
                    break;
                case 4:
                    running = false;
                    break; 
                default:
                    System.out.println("Такого типа посылки не существует. Введите числа от 1 до 3.");
            }
        }
        
    }

    
    private static void sendParcels() {
        // Пройти по allParcels, вызвать packageItem() и deliver()
        if (allParcels.isEmpty()) {
            System.out.println("Посылок нет");
        } else {
            for (Parcel parcel : allParcels) {
                System.out.println("->".repeat(10));
                parcel.packageItem();
                parcel.deliver();
                System.out.println("->".repeat(10));
            }
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

    private static void showLocationAllTracableParcels() {
        if (tracableParcels.isEmpty()) {
            System.out.println("*".repeat(20)); 
            System.out.println("Отслеживаемых посылок нет.");
            System.out.println("*".repeat(20)); 
        } else {
            int i = 1; 
            System.out.println("*".repeat(20));
            System.out.println("В списке отслеживаемых посылок " + tracableParcels.size() + " предметов");
            for (Tracable tracable : tracableParcels) {
                System.out.println("*".repeat(20));
                System.out.println(i + ". Введите новое местоположение отслеживаемой посылки\n" + tracable);
                System.out.println("~>".repeat(10));
                System.out.print("Новое местопложение: ");
                tracable.reportStatus(scanner.nextLine());
                System.out.println("*".repeat(20));
                i++;
            }
        }
    }

    private static void showBoxContent() {
        boolean runnig = true;
        while(runnig) {
            showMenuBoxContent();
            int choice = Integer.parseInt(scanner.nextLine().strip());
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
                    System.out.println("Неверная команда");
            }
        }
    }
    private static void showMenuBoxContent() {
        System.out.println("+".repeat(20));
        System.out.println("Содержимое какой коробки вы хотите посмотреть?");
        System.out.println("1. Коробка со стандартными посылками");
        System.out.println("2. Коробка с хрупкими посылками");
        System.out.println("3. Коробка со скоропортящимися посылками");
        System.out.println("0. Выход");
        System.out.println("+".repeat(20));
    }
    private static <T extends Parcel> void boxContent(ParcelBox<T> parcelBox){
        int parcelsNumberInBox = parcelBox.getNumberOfParcels();
        String parcelsTypeInBox = parcelBox.getParcelsBoxType();
        int i = 1;
        if (parcelsNumberInBox == 0) {
            System.out.println("Эта коробка для посылок пуста.");
        } else {
            System.out.println("Это коробка для посылок типа '" + parcelsTypeInBox +"'. В ней " + parcelsNumberInBox + " предметов.");
            System.out.println("=".repeat(20));
            for(T parcel : parcelBox.getAllParcels()) {
                System.out.println("Посылка №" + i);
                System.out.println("=".repeat(20));
                System.out.println(parcel);
                System.out.println("=".repeat(20));
                i++; 
            }
        }
    }
    
    private static String addParcelsUserInputString(String enterDataHello) {
        String userStringInput = "";
        System.out.println("_".repeat(20));
        System.out.println(enterDataHello);
        while (true) {
            userStringInput = scanner.nextLine();
            System.out.println("_".repeat(20));
            if (userStringInput.equals("")) {
                System.out.println("Нужно ввести не пустую строку :3");
            } else {
                return userStringInput;  
            }
        }
    }

    private static int addParcelsUserInputInt(String enterDataHello) {
        int positiveNumber = 0;
        System.out.println("_".repeat(20));
        System.out.println(enterDataHello);
        while(true) {
            positiveNumber = Integer.parseInt(scanner.nextLine().strip());
            System.out.println("_".repeat(20));
            if (positiveNumber > 0) {
                return positiveNumber;
            } else {
                System.out.println("Введите положительное число");
            }
        }
        
    }
}
