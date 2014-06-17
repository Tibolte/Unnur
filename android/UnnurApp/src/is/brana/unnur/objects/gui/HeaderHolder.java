package is.brana.unnur.objects.gui;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import is.brana.unnur.R;
import is.brana.unnur.utils.Utils;

/**
 * Created by thibaultguegan on 26/05/2014.
 */
public class HeaderHolder {

    public ImageButton btnLeft;
    public TextView txtHeader;
    public ImageButton btnRight;
    public RelativeLayout background;

    public HeaderHolder(Activity activity, String title)
    {
        this.btnLeft = (ImageButton)activity.findViewById(R.id.btn_menuback);
        this.txtHeader = (TextView)activity.findViewById(R.id.textheader);
        this.btnRight = (ImageButton)activity.findViewById(R.id.btn_right);
        this.background = (RelativeLayout)activity.findViewById(R.id.header);

        this.txtHeader.setTypeface(Utils.getFont(activity, Utils.ROBOTO_REGULAR));
        this.txtHeader.setText(title);
    }

    public void setTitle(String title)
    {
        this.txtHeader.setText(title);
    }

    public void setBtnLeft(Drawable drawable) {
        this.btnLeft.setImageDrawable(drawable);
    }

    public TextView getTxtHeader() {
        return txtHeader;
    }

    public void setTxtHeader(TextView txtHeader) {
        this.txtHeader = txtHeader;
    }

    public ImageButton getBtnRight() {
        return btnRight;
    }

    public void setBtnRight(ImageButton btnRight) {
        this.btnRight = btnRight;
    }

    public void setBackgroundAlpha(int alpha)
    {
        this.background.getBackground().setAlpha(alpha);
    }
}