package com.example.fishing;

import com.example.fishing.models.DocUrl;
import com.example.fishing.models.FZ44;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class HelloController {
    FZ44 fz44 = new FZ44();
    @FXML
    public CheckBox dateonly;
    @FXML
    private ListView<FZ44> itemHolder;
    @FXML
    private Button take = new Button();
    @FXML
    private Button save;
    @FXML
    private ProgressBar progbar;
    @FXML
    private DatePicker shedule;

    String db = "jdbc:postgresql://localhost:5432/tenders";
    String username = "postgres";
    String password = "whoami789";


//    public String getDate() {
//
//        return mydate;
//    }

    @FXML
    protected void take() throws Exception {

        take.setOnAction(actionEvent -> {


            try {
                LocalDate date = shedule.getValue();
                String mydate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                System.out.println(mydate);
                final SyndFeed[] feed = new SyndFeed[1];
                feed[0] = getSyndFeedForUrl("https://zakupki.gov.ru/epz/order/extendedsearch/rss.html?morphology=on&sortBy=UPDATE_DATE&pageNumber=1&sortDirection=false&recordsPerPage=_10&showLotsInfoHidden=false&fz44=on&af=on&ca=on&pc=on&pa=on&priceContractAdvantages44IdNameHidden=%7B%7D&priceContractAdvantages94IdNameHidden=%7B%7D&currencyIdGeneral=-1&publishDateFrom=" + mydate + "&publishDateTo=" + mydate + "&delKladrIds=5277338&delKladrIdsCodes=11000000000&selectedSubjectsIdNameHidden=%7B%7D&okdpGroupIdsIdNameHidden=%7B%7D&koksIdsIdNameHidden=%7B%7D&OrderPlacementSmallBusinessSubject=on&OrderPlacementRnpData=on&OrderPlacementExecutionRequirement=on&orderPlacement94_0=0&orderPlacement94_1=0&orderPlacement94_2=0&contractPriceCurrencyId=-1&budgetLevelIdNameHidden=%7B%7D&nonBudgetTypesIdNameHidden=%7B%7D");
                List res = feed[0].getEntries();
                ObservableList<FZ44> fz44ObsList = FXCollections.observableArrayList();
                // Connection conn = DriverManager.getConnection(db, username, password);
                System.out.println("ok");

                String amount = "";
                String docNumber = "";
                String href = "";
                String ename = "";
                String purchaseObjectInfo = "";
                String orgName = "";
                String maxPrice = "";
                String docurl = "";
                String docname = "";
                String publishDate = "";
                String inn = "";
                String kpp = "";
                String ikz = "";
                String contactPersonName = "";
                String contactPersonFirstName = "";
                String contactPersonEmail = "";
                String contactPersonPhone = "";

                System.out.println((long) feed[0].getEntries().size());
                String url;
                for (Object o : res) {
                    ArrayList<DocUrl> docUrlList = new ArrayList<>();
                    var a = ((SyndEntryImpl) o).getDescription().getValue();

                    String[] parts = a.split("<strong>");
                    String lastOne = parts[parts.length - 10];
                    String finish = lastOne.replaceAll("[^0-9]", "");
                    url = "https://zakupki.gov.ru/epz/order/notice/printForm/viewXml.html?regNumber=" + finish + "";

                    //DOM parser
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    Document doc = dbf.newDocumentBuilder().parse(url);

                    Node root = doc.getDocumentElement();

                    NodeList rootNode = root.getChildNodes();
                    NodeList commonInfo;
                    NodeList placingWay;
                    NodeList notificationInfo;
                    NodeList customerRequirementsInfo;
                    NodeList customerRequirementInfo;
                    NodeList customer;
                    NodeList contractConditionsInfo;
                    NodeList maxPriceInfo;
                    NodeList applicationGuarantee;
                    NodeList attachmentsInfo;
                    NodeList attachments;
                    NodeList docKindInfo;
                    NodeList responsibleOrgInfo;
                    NodeList responsibleInfo;
                    NodeList purchaseResponsibleInfo;
                    NodeList ikz_node;
                    NodeList contract_conditionsInfo;
                    NodeList contactPersonInfo;


                    for (int i = 0; i < rootNode.getLength(); i++) {
                        switch (rootNode.item(i).getNodeName()) {
                            case "commonInfo" -> {
                                commonInfo = rootNode.item(i).getChildNodes();
                                for (int j = 0; j < commonInfo.getLength(); j++) {
                                    if (commonInfo.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                        switch (commonInfo.item(j).getNodeName()) {
                                            case "purchaseNumber" -> {
                                                docNumber = commonInfo.item(j).getTextContent();
                                            }
                                            case "href" -> {
                                                href = commonInfo.item(j).getTextContent();
                                            }
                                            case "placingWay" -> {
                                                placingWay = commonInfo.item(j).getChildNodes();
                                                for (int k = 0; k < placingWay.getLength(); k++) {
                                                    if (placingWay.item(k).getNodeType() == Node.ELEMENT_NODE) {
                                                        if ("ns2:name".equals(placingWay.item(k).getNodeName())) {
                                                            ename = placingWay.item(k).getTextContent();
                                                        }
                                                    }
                                                }
                                            }
                                            case "purchaseObjectInfo" -> {
                                                purchaseObjectInfo = commonInfo.item(j).getTextContent();
                                            }
                                            case "plannedPublishDate" -> {
                                                publishDate = commonInfo.item(j).getTextContent();//
                                            }
                                        }
                                    }
                                }
                            }
                            case "purchaseResponsibleInfo" -> {
                                purchaseResponsibleInfo = rootNode.item(i).getChildNodes();
                                for (int j = 0; j < purchaseResponsibleInfo.getLength(); j++) {
                                    if (purchaseResponsibleInfo.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                        switch (purchaseResponsibleInfo.item(j).getNodeName()) {
                                            case "responsibleOrgInfo" -> {
                                                responsibleOrgInfo = purchaseResponsibleInfo.item(j).getChildNodes();
                                                for (int k = 0; k < responsibleOrgInfo.getLength(); k++) {
                                                    if (responsibleOrgInfo.item(k).getNodeType() == Node.ELEMENT_NODE) {
                                                        switch (responsibleOrgInfo.item(k).getNodeName()) {
                                                            case "INN" ->
                                                                    inn = responsibleOrgInfo.item(k).getTextContent();
                                                            case "KPP" ->
                                                                    kpp = responsibleOrgInfo.item(k).getTextContent();
                                                        }
                                                    }
                                                }
                                            }
                                            case "responsibleInfo" -> {
                                                responsibleInfo = purchaseResponsibleInfo.item(j).getChildNodes();
                                                for (int k = 0; k < responsibleInfo.getLength(); k++) {
                                                    if (responsibleInfo.item(k).getNodeType() == Node.ELEMENT_NODE) {
                                                        switch (responsibleInfo.item(k).getNodeName()) {
                                                            case "contactPersonInfo" -> {
                                                                contactPersonInfo = responsibleInfo.item(k).getChildNodes();
                                                                for (int l = 0; l < contactPersonInfo.getLength(); l++) {
                                                                    if (contactPersonInfo.item(l).getNodeType() == Node.ELEMENT_NODE) {
                                                                        switch (contactPersonInfo.item(l).getNodeName()) {
                                                                            case "ns3:lastName" ->
                                                                                    contactPersonName = contactPersonInfo.item(l).getTextContent();
                                                                            case "ns3:firstName" ->
                                                                                    contactPersonFirstName = contactPersonInfo.item(l).getTextContent();
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            case "contactEMail" ->
                                                                    contactPersonEmail = responsibleInfo.item(k).getTextContent();
                                                            case "contactPhone" ->
                                                                    contactPersonPhone = responsibleInfo.item(k).getTextContent();
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                    }

                                }
                            }

                            case "notificationInfo" -> {
                                notificationInfo = rootNode.item(i).getChildNodes();
                                for (int j = 0; j < notificationInfo.getLength(); j++) {
                                    if (notificationInfo.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                        switch (notificationInfo.item(j).getNodeName()) {
                                            case "customerRequirementsInfo" -> {
                                                customerRequirementsInfo = notificationInfo.item(j).getChildNodes();
                                                for (int k = 0; k < customerRequirementsInfo.getLength(); k++) {
                                                    if (customerRequirementsInfo.item(k).getNodeType() == Node.ELEMENT_NODE) {
                                                        if ("customerRequirementInfo".equals(customerRequirementsInfo.item(k).getNodeName())) {
                                                            customerRequirementInfo = customerRequirementsInfo.item(k).getChildNodes();
                                                            for (int l = 0; l < customerRequirementInfo.getLength(); l++) {
                                                                if (customerRequirementInfo.item(l).getNodeType() == Node.ELEMENT_NODE) {
                                                                    switch (customerRequirementInfo.item(l).getNodeName()) {
                                                                        case "customer" -> {
                                                                            customer = customerRequirementInfo.item(l).getChildNodes();
                                                                            for (int li = 0; li < customer.getLength(); li++) {
                                                                                if (customer.item(li).getNodeType() == Node.ELEMENT_NODE) {
                                                                                    if ("ns2:fullName".equals(customer.item(li).getNodeName())) {
                                                                                        orgName = customer.item(li).getTextContent();
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                        case "applicationGuarantee" -> {
                                                                            applicationGuarantee = customerRequirementInfo.item(l).getChildNodes();
                                                                            for (int li = 0; li < applicationGuarantee.getLength(); li++) {
                                                                                if (applicationGuarantee.item(li).getNodeType() == Node.ELEMENT_NODE) {
                                                                                    if ("amount".equals(applicationGuarantee.item(li).getNodeName())) {
                                                                                        amount = applicationGuarantee.item(li).getTextContent();
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                        case "contractConditionsInfo" -> {
                                                                            contract_conditionsInfo = customerRequirementInfo.item(l).getChildNodes();
                                                                            for (int li = 0; li < contract_conditionsInfo.getLength(); li++) {
                                                                                if (contract_conditionsInfo.item(li).getNodeType() == Node.ELEMENT_NODE) {
                                                                                    if ("IKZInfo".equals(contract_conditionsInfo.item(li).getNodeName())) {
                                                                                        ikz_node = contract_conditionsInfo.item(li).getChildNodes();
                                                                                        for (int k1 = 0; k1 < ikz_node.getLength(); k1++) {
                                                                                            if (ikz_node.item(k1).getNodeType() == Node.ELEMENT_NODE) {
                                                                                                if ("purchaseCode".equals(ikz_node.item(k1).getNodeName())) {
                                                                                                    ikz = ikz_node.item(k1).getTextContent();
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            case "contractConditionsInfo" -> {
                                                contractConditionsInfo = notificationInfo.item(j).getChildNodes();
                                                for (int k = 0; k < contractConditionsInfo.getLength(); k++) {
                                                    if (contractConditionsInfo.item(k).getNodeType() == Node.ELEMENT_NODE) {
                                                        if ("maxPriceInfo".equals(contractConditionsInfo.item(k).getNodeName())) {
                                                            maxPriceInfo = contractConditionsInfo.item(k).getChildNodes();
                                                            for (int l = 0; l < maxPriceInfo.getLength(); l++) {
                                                                if (maxPriceInfo.item(l).getNodeType() == Node.ELEMENT_NODE) {
                                                                    if ("maxPrice".equals(maxPriceInfo.item(l).getNodeName())) {
                                                                        maxPrice = maxPriceInfo.item(l).getTextContent();
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            case "attachmentsInfo" -> {
                                attachmentsInfo = rootNode.item(i).getChildNodes();
                                for (int j = 0; j < attachmentsInfo.getLength(); j++) {
                                    if (attachmentsInfo.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                        if ("ns3:attachmentInfo".equals(attachmentsInfo.item(j).getNodeName())) {
                                            attachments = attachmentsInfo.item(j).getChildNodes();
                                            for (int k = 0; k < attachments.getLength(); k++) {
                                                switch (attachments.item(k).getNodeName()) {
                                                    case "ns3:url" -> {
                                                        docurl = attachments.item(k).getTextContent();
                                                    }
                                                    case "ns3:docKindInfo" -> {
                                                        docKindInfo = attachments.item(k).getChildNodes();
                                                        for (int l = 0; l < docKindInfo.getLength(); l++) {
                                                            if ("ns2:name".equals(docKindInfo.item(l).getNodeName())) {
                                                                docname = docKindInfo.item(l).getTextContent();
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        DocUrl docs = new DocUrl(docurl, docname);
                                        docUrlList.add(docs);
                                    }
                                }
                            }
                        }
                        fz44.setTenderid(docNumber);
                        fz44.setArticle(purchaseObjectInfo);
                        fz44.setAmount(amount);
                        fz44.setTendertype(ename);
                        fz44.setClientname(orgName);
                        fz44.setMaxPrice(maxPrice);
                        fz44.setUrl(href);
                        fz44.setDocUrl(docUrlList);
                        fz44.setInn(inn);
                        fz44.setKpp(kpp);
                        fz44.setPublishDate(publishDate);
                        fz44.setIkz(ikz);
                        fz44.setPerson_name(contactPersonName);
                        fz44.setPerson_first_name(contactPersonFirstName);
                        fz44.setPhone(contactPersonPhone);
                        fz44.setEmail(contactPersonEmail);
                        fz44.setActive(false);
                    }
                    System.out.println("======>");
                    System.out.println(fz44);

                    fz44ObsList.addAll(new FZ44(fz44.getTenderid(), fz44.getTendertype(),
                            fz44.getClientname(), fz44.getArticle(), fz44.getMaxPrice(), (ArrayList<DocUrl>) fz44.getDocUrl(), fz44.getActive()));
                    itemHolder.setItems(fz44ObsList);
                    itemHolder.setCellFactory(fzListView -> new FZ44Controller());
                    itemHolder.editableProperty().setValue(false);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        save.setOnAction(actionEvent -> {
            int i = 0;
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(db, username, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (itemHolder.getItems().get(i).getActive()) {

                for (int h = 0; h < itemHolder.getItems().size(); h++) {
                    String sql = "insert into tenders (tenderid, article, tendertype, summcontract) values (?,?,?,?)";
                    PreparedStatement preparedStatement = null;
                    try {
                        System.out.println(i);
                        preparedStatement = conn.prepareStatement(sql);
                        preparedStatement.setString(1, String.valueOf(itemHolder.getItems().get(i).tenderid));
                        preparedStatement.setString(2, fz44.getArticle());
                        preparedStatement.setString(3, fz44.getTendertype());
                        preparedStatement.setFloat(4, Float.parseFloat(fz44.getMaxPrice()));
                        int rows = preparedStatement.executeUpdate();
                        System.out.printf("%d rows added\n", rows);
                        System.out.println(itemHolder.getItems().get(i));


                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    File file = new File("\\\\192.168.0.30\\Accept\\??????????????\\files\\" + itemHolder.getItems().get(i).tenderid);

                    boolean status = file.exists();
                    try {
                        if (!status) {
                            file.mkdir();
                            //FileUtil.fileupload(path);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    for (int a = 0; a < itemHolder.getItems().get(i).getDocUrl().size(); a++) {
                        System.out.println(a);
                        try {
                            URL url = new URL(itemHolder.getItems().get(i).getDocUrl().get(a).getUrl()); /*fz44.getDocUrl().get(a).getUrl()*/
                            URLConnection connection = url.openConnection(); //?????????????????????????? ????????????????????
                            connection.setDoOutput(true);
                            File file_exists = new File(file + "\\" + itemHolder.getItems().get(i).getDocUrl().get(a).getDocName() + ".docx");

                            //???????????????? InputStream, ?????????? ???????????? ???? ???????? ???????????? ????????????
                            connection.setReadTimeout(2000);
                            InputStream inputStream = url.openStream();
                            if (!file_exists.exists()) {
                                Files.copy(inputStream, new File(file + "\\" + itemHolder.getItems().get(i).getDocUrl().get(a).getDocName() + ".docx").toPath());
                                inputStream.close();
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    i++;
                }
            } else if (!itemHolder.getItems().get(i).getActive()) {
                for (int h = 0; h < itemHolder.getItems().size(); h++) {
                    try {
                        conn = DriverManager.getConnection(db, username, password);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    String sql = "insert into tenders (tenderid, article, tendertype, summcontract) values (?,?,?,?)";
                    PreparedStatement preparedStatement = null;
                    try {
                        System.out.println(i);
                        preparedStatement = conn.prepareStatement(sql);
                        preparedStatement.setString(1, String.valueOf(itemHolder.getItems().get(i).tenderid));
                        preparedStatement.setString(2, fz44.getArticle());
                        preparedStatement.setString(3, fz44.getTendertype());
                        preparedStatement.setFloat(4, Float.parseFloat(fz44.getMaxPrice()));
                        int rows = preparedStatement.executeUpdate();
                        System.out.printf("%d rows added\n", rows);
                        System.out.println(itemHolder.getItems().get(i));


                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }


    public static SyndFeed getSyndFeedForUrl(String url) throws Exception {
        SyndFeed feed = null;
        InputStream is = null;

        try {

            URLConnection openConnection = new URL(url).openConnection();
            is = new URL(url).openConnection().getInputStream();
            if ("gzip".equals(openConnection.getContentEncoding())) {
                is = new GZIPInputStream(is);
            }
            InputSource source = new InputSource(is);
            SyndFeedInput input = new SyndFeedInput();
            feed = input.build(source);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) is.close();
        }

        return feed;
    }
}