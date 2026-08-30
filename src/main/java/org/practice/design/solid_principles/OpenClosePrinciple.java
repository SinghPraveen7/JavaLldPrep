package org.practice.design.solid_principles;

/**
 * Open/Closed Principle(OCP) states that “A class should be open for extension but closed for modification.”
 * This means that new functionality can be added through extension (inheritance, composition, interfaces) rather
 * than by modifying existing code. As existing code is already tested and deployed in production, adding
 * modifications would introduce an additional risk and require additional testing effort.
 *
 * Systems designed with OCP in mind enhance robustness and maintainability, increase the longevity and scalability
 * of software solutions, and reduce the risk of introducing defects during changes.
 *
 */
public class OpenClosePrinciple {

    public static void main(String[] args) {
        System.out.println("Open close principle states that a class should be open " +
                "for extension but close for modification.");
        System.out.println("##############################################################");
        System.out.println("Following is the bad example if OCP");
        InvoiceRepo invoiceRepo = new InvoiceRepo();
        invoiceRepo.saveToSqlDb();
        invoiceRepo.saveToMongoDb();
        invoiceRepo.saveToElastic();
        System.out.println("##############################################################");
        System.out.println("Following is the good example if OCP");
        InvoiceRepository invoiceRepositorySql = new InvoiceSQLRepo();
        invoiceRepositorySql.save();
        InvoiceRepository invoiceRepositoryMongo = new InvoiceMongoRepo();
        invoiceRepositoryMongo.save();
        InvoiceRepository invoiceRepositoryElastic = new InvoiceElasticRepo();
        invoiceRepositoryElastic.save();
    }

    // Same class is handling all type of save operation to different sources,
    // if in future we need to onboard a new source we need to modify this class
    static class InvoiceRepo {

        void saveToSqlDb() {
            System.out.println("Saving data in SQL Db!");
        }

        void saveToMongoDb() {
            System.out.println("Saving data in Mongo Db!");
        }

        void saveToElastic() {
            System.out.println("Saving data in Elastic search!");
        }

    }

    interface InvoiceRepository {

        public void save();

    }

    static class InvoiceSQLRepo implements InvoiceRepository {

        @Override
        public void save() {
            System.out.println("Saving data in SQL Db!");
        }

    }

    static class InvoiceMongoRepo implements InvoiceRepository {

        @Override
        public void save() {
            System.out.println("Saving data in Mongo Db!");
        }

    }

    static class InvoiceElasticRepo implements InvoiceRepository {

        @Override
        public void save() {
            System.out.println("Saving data in Elastic search!");
        }

    }

}
