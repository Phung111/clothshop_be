package com.phung.clothshop.domain.dto.product;

import lombok.*;

import java.text.ParseException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

import com.phung.clothshop.domain.entity.product.Discount;
import com.phung.clothshop.domain.entity.product.ECategory;
import com.phung.clothshop.domain.entity.product.EColor;
import com.phung.clothshop.domain.entity.product.EProductStatus;
import com.phung.clothshop.domain.entity.product.ESize;
import com.phung.clothshop.domain.entity.product.Product;
import com.phung.clothshop.domain.entity.productDetail.ECountry;
import com.phung.clothshop.domain.entity.productDetail.ESeason;
import com.phung.clothshop.domain.entity.productDetail.EShipsFrom;
import com.phung.clothshop.domain.entity.productDetail.EStyle;
import com.phung.clothshop.domain.entity.productDetail.ETopLength;
import com.phung.clothshop.domain.entity.productDetail.ProductDetail;
import com.phung.clothshop.utils.DateFormat;
import com.phung.clothshop.utils.customAnnotation.EnumValidCheck;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductCreateReqDTO {

    private Long id;

    @NotBlank(message = "name product can not blank")
    private String name;

    @NotBlank(message = "price can not blank")
    @Pattern(regexp = "^(0|[1-9][0-9]*)$", message = "price product is not valid number")
    @Pattern(regexp = "^(1000|[1-9]\\d{3,6})$", message = "price must be between 1.000 and 10.000.000")
    private String price;

    @NotBlank(message = "quantity can not blank")
    @Pattern(regexp = "^(0|[1-9][0-9]*)$", message = "quantity product is not valid number")
    @Pattern(regexp = "^(?:[1-9]|[1-9]\\d{1,2}|1000)$", message = "quantity must be between 1 and 1.000")
    private String quantity;

    private Long sold = (long) 0;

    private Boolean deleted = false;

    private String decription;

    @NotBlank(message = "category can not blank")
    @EnumValidCheck(enumClass = ECategory.class, message = "Invalid category value")
    private String eCategory;

    @NotBlank(message = "color can not blank")
    @EnumValidCheck(enumClass = EColor.class, message = "Invalid color value")
    private String eColor;

    @NotBlank(message = "size can not blank")
    @EnumValidCheck(enumClass = ESize.class, message = "Invalid size value")
    private String eSize;

    private String eProductStatus = EProductStatus.AVAIABLE.toString();

    @NotBlank(message = "top length can not blank")
    @EnumValidCheck(enumClass = ETopLength.class, message = "Invalid top length value")
    private String eTopLength;

    @NotBlank(message = "country can not blank")
    @EnumValidCheck(enumClass = ECountry.class, message = "Invalid country value")
    private String eCountry;

    @NotBlank(message = "season can not blank")
    @EnumValidCheck(enumClass = ESeason.class, message = "Invalid season value")
    private String eSeason;

    @NotBlank(message = "style can not blank")
    @EnumValidCheck(enumClass = EStyle.class, message = "Invalid style value")
    private String eStyle;

    @NotBlank(message = "ships from can not blank")
    @EnumValidCheck(enumClass = EShipsFrom.class, message = "Invalid ships from value")
    private String eShipsFrom;

    private MultipartFile[] multipartFiles;

 
    private String dateStart;


    private String dateEnd;

    @Pattern(regexp = "^(0|[1-9][0-9]*)$", message = "percent is not valid number")
    @Pattern(regexp = "^(100|[1-9]?[0-9])$", message = "percent must be between 0 and 100")
    private String percent;

    public Product toProduct() {

        ProductDetail productDetail = new ProductDetail();
        productDetail.setETopLength(ETopLength.valueOf(eTopLength));
        productDetail.setECountry(ECountry.valueOf(eCountry));
        productDetail.setESeason(ESeason.valueOf(eSeason));
        productDetail.setEStyle(EStyle.valueOf(eStyle));
        productDetail.setEShipsFrom(EShipsFrom.valueOf(eShipsFrom));

        return new Product()
                .setId(id)
                .setName(name)
                .setPrice(Long.valueOf(price))
                .setQuantity(Long.valueOf(quantity))
                .setSold(sold)
                .setDecription(decription)
                .setECategory(ECategory.valueOf(eCategory))
                .setEColor(EColor.valueOf(eColor))
                .setESize(ESize.valueOf(eSize))
                .setEProductStatus(EProductStatus.valueOf(eProductStatus))
                .setProductDetail(productDetail);

    }

    public Discount toDiscount() throws NumberFormatException, ParseException {
        return new Discount()
                .setDateStart(DateFormat.parse(dateStart))
                .setDateEnd(DateFormat.parse(dateEnd))
                .setPercent(Long.valueOf(percent));
    }

}
