package example;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class SalaryCalculations {


    private List<PaymentDetails> listOfCreditor;


    public void setListOfCreditor(List<PaymentDetails> listOfCreditor) {
        this.listOfCreditor = listOfCreditor;
    }


    public static File createFile(String path) throws Exception {
        File file = new File(path);
        if (file.createNewFile()) {
        } else {
        }
        return file;
    }


    public static PaymentDetails createPayment(String debtorOrCreditor, String depositNumber, Integer amount) {
        PaymentDetails Payment = new PaymentDetails();
        Payment.setAmount(amount);
        Payment.setDepositNumber(depositNumber);
        Payment.setDebtorOrCreditor(debtorOrCreditor);

        return Payment;
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

    public static void main(String[] args) throws Exception {

        Scanner reader = new Scanner(createFile("D:\\project\\javaFirst\\folder\\paymentFile.txt"));

        List<PaymentDetails> listOfObj = changeStringToObj(reader);

        List<List<PaymentDetails>> lists = new ArrayList();

        List<PaymentDetails> list = new ArrayList();

        int i = 1;
        for (PaymentDetails info : listOfObj) {

            if (i % 10 == 0 && i != 0) {
                list.add(info);
                lists.add(list);
                list = new ArrayList();
                i++;
            } else {
                list.add(info);
                i++;
            }
        }

        for (List info : lists) {
            SalaryInfo salaryInfo = new SalaryInfo(info);
            Thread thread = new Thread(salaryInfo);
            thread.start();
        }
    }
}