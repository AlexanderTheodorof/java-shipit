abstract class Parcel {
    protected final static int deliveryCost = 1;
    protected String description;
    protected String deliveryAddress;
    protected String type; 
    protected int    weight;
    protected int    sendDay;
    
    

    Parcel(String description, String deliveryAddress, int weight, int sendDay) {
        this.description     = description;
        this.deliveryAddress = deliveryAddress;
        this.weight          = weight;
        this.sendDay         = sendDay;
    }

    public int getDeliveryCost() {
        return deliveryCost;
    }
    void packageItem() { // упаковать
        // packageItem — «упаковать». Для стандартных и скоропортящихся посылок этот метод должен просто выводить на экран текст Посылка <<XXX>> упакована, а для хрупких посылок — строку Посылка <<XXX>> обёрнута в защитную плёнку, а затем Посылка <<XXX>> упакована, где XXX — описание посылки.
        System.out.println("Посылка '" + description + "' упакована"); 
    }            
    
    public void deliver() {
        // deliver — «доставить». Этот метод будет осуществлять доставку посылки адресату. Он должен выводить на экран текст Посылка <<XXX>> доставлена по адресу YYY, где ХХХ — описание посылки, а YYY — адрес назначения.
        System.out.println("Посылка '" + description + "' доставлена по адресу '" + deliveryAddress + "'."); 
    }
    
    public int calculateDeliveryCost() { // рассчитать стоимость отправки
        // calculateDeliveryCost — «вычислить стоимость доставки». Стоимость доставки должна вычисляться следующим образом: вес посылки умножается на базовую стоимость одной единицы отправления. Базовая стоимость фиксирована для каждого типа посылок и равна 2 для стандартной посылки, 3 для скоропортящейся и 4 для хрупкой. Подумайте, как избежать дублирования кода при реализации этого метода для разных типов посылок.
        return getDeliveryCost()*weight; 
    }

    @Override
    public String toString() {
        String objectDescript = type               + "\n" 
            + description                          + "\n" 
            + "Вес посылки:\t"           + weight  + "\n"
            + "Посылка отправлена:\t"    + sendDay + "\n"
            + "Стоимость отправления:\t" + calculateDeliveryCost();
                      
        return objectDescript;
    }

    public int getWeight() {
        return weight; 
    }

    public String getType(){
        return  type;
    }

}
