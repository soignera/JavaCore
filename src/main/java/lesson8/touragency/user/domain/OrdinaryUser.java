package lesson8.touragency.user.domain;

public class OrdinaryUser extends User {
    private String FoodCount;
    private String SeatSize;

    public String getFoodCount() {
        return FoodCount;
    }

    public void setFoodCount(String foodCount) {
        FoodCount = foodCount;
    }

    public String getSeatSize() {
        return SeatSize;
    }

    public void setSeatSize(String seatSize) {
        SeatSize = seatSize;
    }
}
