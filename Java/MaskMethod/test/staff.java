import com.pmbank.eyas.common.sensitive
import com.pmbank.eyas.common.sensitive.Mask;
import com.pmbank.eyas.common.sensitive.MaskType;

import java.lang.String;


/**
 * Created by lfp on 2020/5/13.
 */
public class staff {

    @Mask (MaskType.CHN_NAME)
    private String name;

    @Mask (MaskType.ADDRESS)
    private  String address;

    @Mask (MaskType.ID_CARD)
    private  String idCard;

    @Mask (MaskType.PASSWORD)
    private String password;

    @Mask (MaskType.EMAIL)
    private String email;

    @Mask (MaskType.PHONE_MOBILE)
    private  String MobileNum;

    @Mask (MaskType.BANK_CARD)
    private  String bankCard;

    @Mask (leftKeep = 0,rightKeep = 4)
    private  String officephone;
    .....



}
