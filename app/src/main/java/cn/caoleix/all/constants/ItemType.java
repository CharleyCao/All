package cn.caoleix.all.constants;

/**
  * @author charley
  * @date 2020/2/12 22:46
  * @desc
  */
public enum ItemType {

    article(0),
    demo(1);

    private int code;

    private ItemType(int code) {
        this.code = code;
    }

    public static ItemType by(int code) {
        ItemType[] values = values();
        for (ItemType itemType : values) {
            if (code == itemType.code) {
                return itemType;
            }
        }
        return null;
    }

}
