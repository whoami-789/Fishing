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
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
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
    ArrayList<FZ44> list = new ArrayList<>();
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


    public String getDate() {
        LocalDate date = shedule.getValue();
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @FXML
    protected void take() throws Exception {

        final SyndFeed[] feed = new SyndFeed[1];
        feed[0] = getSyndFeedForUrl("https://zakupki.gov.ru/epz/order/extendedsearch/rss.html?morphology=on&sortBy=UPDATE_DATE&pageNumber=1&sortDirection=false&recordsPerPage=_10&showLotsInfoHidden=false&fz44=on&af=on&ca=on&pc=on&pa=on&priceContractAdvantages44IdNameHidden=%7B%7D&priceContractAdvantages94IdNameHidden=%7B%7D&currencyIdGeneral=-1&publishDateFrom=" + getDate() + "&publishDateTo=" + getDate() + "&delKladrIds=5277338&delKladrIdsCodes=11000000000&selectedSubjectsIdNameHidden=%7B%7D&okdpGroupIdsIdNameHidden=%7B%7D&koksIdsIdNameHidden=%7B%7D&OrderPlacementSmallBusinessSubject=on&OrderPlacementRnpData=on&OrderPlacementExecutionRequirement=on&orderPlacement94_0=0&orderPlacement94_1=0&orderPlacement94_2=0&contractPriceCurrencyId=-1&budgetLevelIdNameHidden=%7B%7D&nonBudgetTypesIdNameHidden=%7B%7D");
        List res = feed[0].getEntries();
        ObservableList<FZ44> fz44ObsList = FXCollections.observableArrayList();
        take.setOnAction(actionEvent -> {


            try {
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


                    }
                    System.out.println("======>");
                    System.out.println(fz44);

                    fz44ObsList.addAll(new FZ44(fz44.getTenderid(), fz44.getTendertype(),
                            fz44.getClientname(), fz44.getArticle(), fz44.getMaxPrice(), (ArrayList<DocUrl>) fz44.getDocUrl()));
                    itemHolder.setItems(fz44ObsList);
                    itemHolder.setCellFactory(fzListView -> new FZ44Controller());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        save.setOnAction(actionEvent -> {
            int i=0;
            for (Object o : res) {
                Connection conn = null;
                try {
                    conn = DriverManager.getConnection(db, username, password);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                String sql = "insert into tenders (tenderid, article, tendertype, summcontract) values (?,?,?,?)";
                PreparedStatement preparedStatement = null;
                try {
                    preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setString(1, String.valueOf(itemHolder.getItems().get(i).tenderid));
                    preparedStatement.setString(2, fz44.getArticle());
                    preparedStatement.setString(3, fz44.getTendertype());
                    preparedStatement.setFloat(4, Float.parseFloat(fz44.getMaxPrice()));
                    int rows = preparedStatement.executeUpdate();
                    System.out.printf("%d rows added\n", rows);
                    System.out.println(itemHolder.getItems().get(i));
                    i++;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
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