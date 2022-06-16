/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Win7
 */
import static java.time.Clock.system;
import jexer.TAction;
import jexer.TApplication;
import jexer.TField;
import jexer.TText;
import jexer.TWindow;
import jexer.event.TMenuEvent;
import jexer.menu.TMenu;

public class TUIdemo extends TApplication {

    private static final int ABOUT_APP = 2000;
    private static final int CUST_INFO = 2010;
    private static final int CUST_LOAN = 2020;
    public static void main(String[] args) throws Exception {
        TUIdemo tdemo = new TUIdemo();
        (new Thread(tdemo)).start();
    }

    public TUIdemo() throws Exception {
        super(BackendType.SWING);

        addToolMenu();
        //custom 'File' menu
        TMenu fileMenu = addMenu("&File");
        fileMenu.addItem(CUST_INFO, "&Customer Info");
        fileMenu.addItem(CUST_LOAN, "&Interest on the loan");
        fileMenu.addDefaultItem(TMenu.MID_SHELL);
        fileMenu.addSeparator();
        fileMenu.addDefaultItem(TMenu.MID_EXIT);
        //end of 'File' menu  

        addWindowMenu();

        //custom 'Help' menu
        TMenu helpMenu = addMenu("&Help");
        helpMenu.addItem(ABOUT_APP, "&About...");
        //end of 'Help' menu 

        setFocusFollowsMouse(true);
        //Customer window
        ShowCustomerDetails();
    }

    @Override
    protected boolean onMenu(TMenuEvent menu) {
        if (menu.getId() == ABOUT_APP) {
            messageBox("About", "\t\t\t\t\t   Just a simple Jexer demo.\n\nCopyright \u00A9 2019 Alexander \'Taurus\' Babich").show();
            return true;
        }
        if (menu.getId() == CUST_INFO) {
            ShowCustomerDetails();
            return true;
        }
        if (menu.getId() == CUST_LOAN) {
            ShowLoanDetails();
            return true;
            }
        return super.onMenu(menu);
    }

    private void ShowCustomerDetails() {
        TWindow custWin = addWindow("Customer Window", 2, 1, 40, 10, TWindow.NOZOOMBOX);
        custWin.newStatusBar("Enter valid customer number and press Show...");
        custWin.addLabel("Enter customer number: ", 2, 2);
        TField custNo = custWin.addField(24, 2, 3, false);
        TText details = custWin.addText("Owner Name: \nAccount Type: \nAccount Balance: ", 2, 4, 38, 8);
        custWin.addButton("&Show", 28, 2, new TAction() {
            @Override
            public void DO() {
                try {
                    int custNum = Integer.parseInt(custNo.getText());
                    //details about customer with index==custNum
                    
                    switch (custNum) {
        case 1: 
            details.setText("Owner Name: John Doe (id="+custNum+")\nAccount Type: 'Checking'\nAccount Balance: $200.00");
            break;
        case 2: 
            details.setText("Owner Name: Nick Bow (id="+custNum+")\nAccount Type: 'Checking'\nAccount Balance: $600.00");
            break;
        case 3: 
            details.setText("Owner Name: Djared Pod (id="+custNum+")\nAccount Type: 'Approved'\nAccount Balance: $2000.00");
            break;   
        default:
            messageBox("Error", "You must provide a valid customer number!").show();
            break;
    }
                    
                } catch (Exception e) {
                    messageBox("Error", "You must provide a valid customer number!").show();
                }
            }
        });
        
        
    }

    private void ShowLoanDetails() {
    TWindow custWin = addWindow("Credit Window", 2, 1, 60, 20, TWindow.NOZOOMBOX);
        custWin.newStatusBar("Enter how much you want to credit and press Show...");
        custWin.addLabel("Enter how much you want to credit: ", 2, 2);
        TField custCr = custWin.addField(43, 2, 5, false);
        custWin.addLabel("For how long do you want to take a loan: ", 2, 4);
        TField custTi = custWin.addField(43, 4, 3, false);
        TText details = custWin.addText("Loan: \nTime(month): \nPayment with interest: ", 2, 10, 38, 5);
        custWin.addButton("&Show", 49, 4, new TAction() {
            @Override
            public void DO() {
                try {
                    int custCredit = Integer.parseInt(custCr.getText());
                    int custTime = Integer.parseInt(custTi.getText());
                    int TC;
                    if (custCredit<=1000) {
                    TC = custCredit + (custTime*((custCredit/100)*15));   
                    details.setText("Loan:"+custCredit+"\nTime(month):"+custTime+"\nPayment with interest: "+TC);
                   }
                    if (1000<custCredit && custCredit<=10000) {
                    TC = custCredit + custTime*((custCredit/100)*10);   
                    details.setText("Loan:"+custCredit+"\nTime(month):"+custTime+"\nPayment with interest: "+TC);
                    }
                    if (10000<custCredit && custCredit<=1000000) {
                      TC = custCredit + custTime*((custCredit/100)*5);   
                    details.setText("Loan:"+custCredit+"\nTime(month):"+custTime+"\nPayment with interest: "+TC);
                    }
                } catch (Exception e) {
                    messageBox("Error", "You must provide a valid customer number!").show();
                }
            }
        });
    }
}
