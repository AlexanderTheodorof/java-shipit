class PerishableParcel extends Parcel {        // скоропортящаяся посылка
    int timeToLive;             // срок годности

    PerishableParcel(String description,
                     String deliveryAddress,
                     int weight,
                     int sendDay,
                     int timeToLive) {
        super(description, deliveryAddress, weight, sendDay);
        this.timeToLive = timeToLive;
        super.type = "Скоропортящаяся посылка";
    }

    @Override
    public int getDeliveryCost() {
        return 3;
    }

    @Override
    public String toString() {
        String objectDescript = super.toString() + "\n"
            + "Срок годности посылки:\t" + timeToLive;
        return objectDescript;
    }
    public boolean isExpired(int currentDay) {
        // Если количество дней с момента отправки меньше или равно timeToLive, посылка считается целой, а если больше — испортившейся.
        //isExpired — метод должен присутствовать только у скоропортящихся посылок. На вход методу передаётся целое число currentDay — номер текущего дня месяца. Если сумма значения поля sendDay и timeToLive больше или равна currentDay, нужно вернуть false (посылка не испортилась), иначе — true (посылка испортилась). Возможный выход за пределы месяца можно игнорировать.
        if (sendDay + currentDay > timeToLive) {
            return false; 
        } else {
            return true;
        }
    }
}
