package org.practice.design.solid_principles;

/**
 * if the class has multiple jobs or responsibilities, changes to one responsibility might affect or break the
 * other critical responsibilities, making the code bloated, fragile, and harder to maintain. Hence, the Single
 * Responsibility Principle(SRP) focuses on restricting the concern to only a single responsibility.
 *
 * The Single Responsibility Principle (SRP) is key to maintainable, testable, and flexible code. It ensures each class
 * has one reason to change, fostering a modular system. SRP isn’t about limiting classes to a single method, but
 * rather about ensuring they have one responsibility, even with multiple methods that align with that purpose.
 *
 */
public class SingleResponsibilityPrinciple {

    public static void main(String[] args) {
        System.out.println("Single Responsibility principle state that a class " +
                "should handle only one responsibility.");
        System.out.println("##############################################################");
        System.out.println("Following class shows bad SRP example!");
        System.out.println("If we need to change any functionality then we need to change and test full class again!");
        InvoiceServiceBadSrp invoiceServiceBadSrp = new InvoiceServiceBadSrp();
        Object badInvoice = invoiceServiceBadSrp.generateInvoice(10, 12.5f);
        invoiceServiceBadSrp.saveToDb(badInvoice);
        invoiceServiceBadSrp.sendInvoiceViaMail(badInvoice);

        System.out.println("##############################################################");
        System.out.println("Following class shows Good SRP example!");
        InvoiceServiceGoodSrp invoiceService = new InvoiceServiceGoodSrp();
        Object invoice = invoiceService.generateInvoice(10, 12.5f);
        InvoiceRepository invoiceRepository = new InvoiceRepository();
        invoiceRepository.saveToDb(invoice);
        MailService mailService = new MailService();
        mailService.sendInvoiceViaMail(invoice);
    }

    // Handling 3 tasks -> Generate invoice, save invoice to DB and send invoice via mail
    static class InvoiceServiceBadSrp {

        // this class accumulates unrelated dependencies
        private Object invoice;
        private Object dbConnection;
        private Object mailConfigs;

        Object generateInvoice(int itemCount, float itemPrice) {
            System.out.println("Generating Invoice for item");
            return itemCount * itemPrice;
        }

        void saveToDb(Object invoice) {
            System.out.println("Saving Invoice item in DB!");
        }

        void sendInvoiceViaMail(Object invoice) {
            System.out.println("Sending invoice via mail...");
        }

    }

    // Handling invoice generation logic
    static class InvoiceServiceGoodSrp {

        private Object invoice;

        float generateInvoice(int itemCount, float itemPrice) {
            System.out.println("Generating Invoice for item");
            return itemCount * itemPrice;
        }

    }

    // Handling send mail logic
    static class MailService {

        private Object mailConfigs;

        void sendInvoiceViaMail(Object invoice) {
            System.out.println("Sending invoice via mail...");
        }

    }

    // Handling save to DB operation
    static class InvoiceRepository {

        private Object DbConnection;

        void saveToDb(Object invoice) {
            System.out.println("Saving invoice to DB!");
        }

    }

}
