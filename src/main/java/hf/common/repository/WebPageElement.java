package hf.common.repository;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Sumit Choudhary on 6/15/2019
 */
@Data
@XmlRootElement(name="uiElements")
@XmlAccessorType(XmlAccessType.FIELD)
public class WebPageElement {
    @XmlElement(name="uiobject")
    private List<PageElement> webElements;

}
