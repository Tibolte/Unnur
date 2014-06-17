package is.brana.unnur.menu;

/**
 * Created by thibaultguegan on 16/05/2014.
 */
public class DrawerItem {

    private int menu;
    private int image;
    private Long position;

    public DrawerItem(int menu, int image, Long position)
    {
        this.menu = menu;
        this.image = image;
        this.position = position;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getMenu() {
        return menu;
    }

    public void setMenu(int menu) {
        this.menu = menu;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }
}
