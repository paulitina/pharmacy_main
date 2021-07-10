package code.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderStatus {
    CART("cart"),
    PLACED("placed"),
    APPROVED("approved"),
    DELIVERED("delivered"),
    FINISHED("finished");

    private String statusType;

    public String getStatusType() {
        return statusType;
    }
}
