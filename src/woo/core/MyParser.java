package woo.core;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

import woo.core.exception.BadEntryException;
import woo.core.exception.*;
// add here more imports if needed

public class MyParser {
    private Store _store;  // ou outra entidade

    MyParser(Store s) {
        _store = s;
    }

    void parseFile(String fileName) throws IOException, BadEntryException, DuplicateClientException, DuplicateSupplierException, NonExistentSupplierException, WrongServiceTypeException, DuplicateProductException, WrongServiceLevelException /* may have other exceptions */ {

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null)
                parseLine(line);
        }
    }

    private void parseLine(String line) throws BadEntryException, DuplicateSupplierException, DuplicateClientException, NonExistentSupplierException, WrongServiceTypeException, DuplicateProductException, WrongServiceLevelException {
        String[] components = line.split("\\|");

        switch(components[0]) {
            case "SUPPLIER":
                parseSupplier(line, components);
                break;

            case "CLIENT":
                parseClient(line, components);
                break;

            case "BOX":
                parseBox(line, components);
                break;

            case "CONTAINER":
                parseContainer(line, components);
                break;

            case "BOOK":
                parseBook(line, components);
                break;

            default:
                throw new BadEntryException("Type of line not supported: " + line);
        }
    }

    // Format: SUPPLIER|id|nome|endereço
    private void parseSupplier(String line, String[] components) throws BadEntryException, DuplicateSupplierException {
        if (components.length != 4)
            throw new BadEntryException("Invalid number of fields in supplier description (4) " + line);

        String supplierKey = components[1];
        String name = components[2];
        String address = components[3];

        _store.registerSupplier(supplierKey, name, address);
    }

    // Format: CLIENT|id|nome|endereço
    private void parseClient(String line, String[] components) throws BadEntryException, DuplicateClientException{
        if (components.length != 4)
            throw new BadEntryException("Invalid number of fields (4) in client description: " + line);

        String clientKey = components[1];
        String name = components[2];
        String address = components[3];

        _store.registerClient(clientKey, name, address);
    }

    // Format: BOX|id|tipo-de-serviço|id-fornecedor|preço|valor-crítico|exemplares
    private void parseBox(String line, String[] components) throws BadEntryException, NonExistentSupplierException,
            WrongServiceTypeException, DuplicateProductException {
        if (components.length != 7)
            throw new BadEntryException("Wrong number of fields in box description  (7) " + line);

        String productKey = components[1];
        String typeServiceString = components[2];
        String supplierKey = components[3];
        int price = Integer.parseInt(components[4]);
        int criticalValue = Integer.parseInt(components[5]);
        int value = Integer.parseInt(components[6]);
        _store.registerBox(productKey, price, criticalValue, supplierKey, typeServiceString, value);
    }

    // Format: BOOK|id|título|autor|isbn|id-fornecedor|preço|valor-crítico|exemplares
    private void parseBook(String line, String[] components) throws BadEntryException, DuplicateProductException, NonExistentSupplierException {
        if (components.length != 9)
            throw new BadEntryException("Invalid number of fields (9) in box description: " + line);
        String productKey = components[1];
        String tittle = components[2];
        String author = components[3];
        String isbn = components[4];
        String supplierKey = components[5];
        int price = Integer.parseInt(components[6]);
        int criticalValue = Integer.parseInt(components[7]);
        int value = Integer.parseInt(components[8]);
        _store.registerBook(productKey, price, criticalValue, supplierKey, tittle, author, isbn, value);

    }

    // Format: CONTAINER|id|tipo-de-serviço|nível-de-serviço|id-fornecedor|preço|valor-crítico|exemplares
    private void parseContainer(String line, String[] components) throws BadEntryException, WrongServiceLevelException, NonExistentSupplierException, WrongServiceTypeException, DuplicateProductException {
        if (components.length != 8)
            throw new BadEntryException("Invalid number of fields (8) in container description: " + line);
        String productKey = components[1];
        String typeServiceString = components[2];
        String qualityServiceString = components[3];
        String supplierKey = components[4];
        int price = Integer.parseInt(components[5]);
        int criticalValue = Integer.parseInt(components[6]);
        int value = Integer.parseInt(components[7]);
        _store.registerContainer(productKey, price, criticalValue, supplierKey, typeServiceString, qualityServiceString, value);
    }
}
