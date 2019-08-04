package hf.common.repository;

import lombok.Data;

import javax.xml.bind.annotation.*;

/**
 * Created by Sumit Choudhary on 6/16/2019.
 */
@Data
@XmlRootElement(name="uiobject")
@XmlAccessorType(XmlAccessType.FIELD)
public class PageElement {
    @XmlAttribute(name="name")
    private String elementName;
    @XmlAttribute(name="type")
    private String locatorType;
    @XmlElement(name="locator")
    private String locator;



}
