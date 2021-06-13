package example;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SalaryInfo implements Runnable {
    private List<PaymentDetails> list;
    private SalaryCalculations salaryCalculations = new SalaryCalculations();
    private List<PaymentDetails> listOfCreditor;

    SalaryInfo(List listInfo) {
        list = listInfo;
    }

    public static void createTransactionFile(List info) throws Exception {
        int i = 0;
        List<PaymentDetails> creditors = info;
        FileWriter myWriter = new FileWriter(createFile("D:\\project\\javaFirst\\folder\\transactionFile.txt"));
        for (PaymentDetails creditor : creditors) {
            myWriter.write(i + "\t" + creditor.getDepositNumber() + "\t" + creditor.getAmount() + "\n");
            i++;
        }
        myWriter.close();
    }

    public static File createFile(String path) throws Exception {
        File file = new File(path);
        if (file.createNewFile()) {
            System.out.println("File created: " + file.getName());
        } else {
            System.out.println("File already exists.");
        }
        return file;
    }

    public void createDebtorFile(PaymentDetails info) {
        try {
            PaymentDetails debtor = info;
            String url = "D:\\project\\javaFirst\\folder\\debtorFile.txt";
            FileWriter myWriter = new FileWriter(createFile(url));
            myWriter.write(debtor.getDebtorOrCreditor() + "\t" + debtor.getDepositNumber() + "\t" + debtor.getAmount() + "\n");
            myWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }


    public static List changeStringToObj(Scanner reader) {
        List<PaymentDetails> list = new ArrayList();
        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            String[] arrOfStr = data.split("\t", 3);
            list.add(createPayment(arrOfStr[0], arrOfStr[1], Integer.parseInt(arrOfStr[2])));
        }
        return list;
    }

    public void paySalaries() {
        try {
            Scanner reader = new Scanner(createFile("D:\\project\\javaFirst\\folder\\paymentFile.txt"));
            List<PaymentDetails> list = changeStringToObj(reader);
            PaymentDetails debtor = null;
            List<PaymentDetails> creditor = new ArrayList();
            int creditorAmount = 0;

            for (PaymentDetails info : list) {
                if (info.getDebtorOrCreditor().equals("debtor")) {
                    debtor = info;
                } else {
                    creditor.add(createPayment(info.getDebtorOrCreditor(), info.getDepositNumber(), info.getAmount()));
                    creditorAmount += info.getAmount();

                }
            }

            if (debtor.getAmount() >= creditorAmount) {

                PaymentDetails info = createPayment(debtor.getDebtorOrCreditor(), debtor.getDepositNumber(), debtor.getAmount() - creditorAmount);
                createDebtorFile(info);
                createTransactionFile(creditor);

            } else {
                System.out.println("پول نیست...");
            }

            reader.close();
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    public static PaymentDetails createPayment(String debtorOrCreditor, String depositNumber, Integer amount) {
        PaymentDetails Payment = new PaymentDetails();
        Payment.setAmount(amount);
        Payment.setDepositNumber(depositNumber);
        Payment.setDebtorOrCreditor(debtorOrCreditor);

        return Payment;
    }


    @Override
    public void run() {
        for (PaymentDetails info : list) {
            if (info.getDebtorOrCreditor().equals("debtor")) {
                createDebtorFile(info);
            } else {
//                    listOfCreditor.add(info);
//                    salaryCalculations.setListOfCreditor(listOfCreditor);
                System.out.println(info.getAmount());
            }
        }
    }
}
