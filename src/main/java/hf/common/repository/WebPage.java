package hf.common.repository;

/**
 * Created by Sumit Choudhary on 6/16/2019
 */

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name="page")
@XmlAccessorType(XmlAccessType.FIELD)
public class WebPage {
    @XmlElement(name = "uiElements")
    private WebPageElement webPageElement;
    @XmlAttribute(name = "name")
    private String pageName;




}
