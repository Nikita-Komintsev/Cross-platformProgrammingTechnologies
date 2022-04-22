package com.company;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        File file = new File("phones.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);

        ReadXml(file, factory, builder, document);

        AddData(file, factory, builder, document);

        ReadXml(file, factory, builder, document);

        DeleXml(file);
    }

    private static void DeleXml(File file) {
        // удаление файла
        System.out.print("\nУдалить файл? Y/N: ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        if (Objects.equals(choice, "Y") || Objects.equals(choice, "y")){
            if (file.delete()) {
                System.out.println(file.getName() + " deleted");
            } else {
                System.out.println(file.getName() + " not deleted");
            }
        }
    }

    private static void ReadXml(File file, DocumentBuilderFactory factory, DocumentBuilder builder, Document document) {
        NodeList phoneNodeList = document.getElementsByTagName("phone");

        List<Phones> phonesList = new ArrayList<>();

        for (int i = 0; i < phoneNodeList.getLength(); i++) {
            if (phoneNodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element phoneElement = (Element) phoneNodeList.item(i);

                Phones phone = new Phones();
                phone.setName(phoneElement.getAttribute("name"));

                NodeList childNodes = phoneElement.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        Element childElement = (Element) childNodes.item(j);

                        switch (childElement.getNodeName()) {
                            case "company" -> {
                                phone.setCompany(childElement.getTextContent());
                            }
                            case "price" -> {
                                phone.setPrice(Integer.parseInt(childElement.getTextContent()));
                            }
                        }
                    }
                }

                phonesList.add(phone);
            }
        }

        phonesList.forEach(System.out::println);
    }

    private static void AddData(File file, DocumentBuilderFactory factory, DocumentBuilder builder, Document document) throws TransformerException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Название телефона: ");
        String namePhone = scanner.nextLine();
        System.out.print("Компания: ");
        String nameCompany = scanner.nextLine();
        System.out.print("Цена: ");
        String namePrice = scanner.nextLine();

        Element root = document.getDocumentElement();

        Element company = document.createElement("company");
        company.setTextContent(nameCompany);

        Element price = document.createElement("price");
        price.setTextContent(namePrice);

        Element phone = document.createElement("phone");
        phone.appendChild(company);
        phone.appendChild(price);
        phone.setAttribute("name", namePhone);

        root.appendChild(phone);

        Transformer tFormer = TransformerFactory.newInstance().newTransformer();
        tFormer.setOutputProperty(OutputKeys.METHOD, "xml");
        Source source = new DOMSource(document);
        Result result = new StreamResult(file);
        tFormer.transform(source, result);
    }
}
