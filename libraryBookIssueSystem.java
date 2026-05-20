import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton; 
import javax.swing.JOptionPane;
import java.util.Date;

// Custom Exceptions defined for the system
class EmptyFieldException extends Exception {
    public EmptyFieldException(String message) { super(message); }
}

class InvalidRollNumberException extends Exception {
    public InvalidRollNumberException(String message) { super(message); }
}

class InvalidDateException extends Exception {
    public InvalidDateException(String message) { super(message); }
}

class NullSelectionException extends Exception {
    public NullSelectionException(String message) { super(message); }
}

class InvalidNameException extends Exception {
    public InvalidNameException(String message) { super(message); }
}

public class libraryBookIssueSystem extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField studentName;
    private JTextField rollNumber; 
    private JTextField bookTitle;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    libraryBookIssueSystem frame = new libraryBookIssueSystem();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public libraryBookIssueSystem() {
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 837, 746);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(18, 18, 20));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Student Name");
        lblNewLabel.setForeground(new Color(220, 220, 220));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel.setBounds(72, 130, 98, 14);
        contentPane.add(lblNewLabel);
        
        JLabel lblRollNumber = new JLabel("Roll Number");
        lblRollNumber.setForeground(new Color(220, 220, 220));
        lblRollNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblRollNumber.setBounds(427, 130, 88, 14);
        contentPane.add(lblRollNumber);
        
        JLabel lblBookTitle = new JLabel("Book Title");
        lblBookTitle.setForeground(new Color(220, 220, 220));
        lblBookTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblBookTitle.setBounds(72, 184, 98, 14);
        contentPane.add(lblBookTitle);
        
        JLabel lblBook = new JLabel("Book Category");
        lblBook.setForeground(new Color(220, 220, 220));
        lblBook.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblBook.setBounds(417, 186, 98, 14);
        contentPane.add(lblBook);
        
        JLabel lblIssueDate = new JLabel("Issue Date");
        lblIssueDate.setForeground(new Color(220, 220, 220));
        lblIssueDate.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblIssueDate.setBounds(72, 325, 98, 14);
        contentPane.add(lblIssueDate);
        
        JLabel lblReturnDate = new JLabel("Return Date");
        lblReturnDate.setForeground(new Color(220, 220, 220));
        lblReturnDate.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblReturnDate.setBounds(417, 325, 98, 14);
        contentPane.add(lblReturnDate);
        
        studentName = new JTextField();
        studentName.setForeground(new Color(41, 97, 49));
        studentName.setBounds(180, 128, 169, 20);
        contentPane.add(studentName);
        studentName.setColumns(10);
        
        rollNumber = new JTextField();
        rollNumber.setForeground(new Color(41, 97, 49));
        rollNumber.setColumns(10);
        rollNumber.setBounds(525, 128, 169, 20);
        
        rollNumber.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String text = rollNumber.getText().trim();
                if (!text.isEmpty()) {
                    try {
                        if (!text.matches("^[Ll]1\\s*[-]?\\s*[FSfs]\\d{2}\\s*[-]?\\s*[A-Za-z]+\\s*[-]?\\s*\\d{3,4}$")) {
							throw new InvalidRollNumberException("Format Error! Use: L1 FXX-XXXX-xxxx\\n(e.g.,L1 FA23-BSE-042)");
                        }
                        
                        String numericIdPart = text.replaceAll("^.*?(?=\\d+$)", "");
                        Integer.parseInt(numericIdPart);
                        
                    } catch (InvalidRollNumberException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Roll Number Pattern Warning", JOptionPane.ERROR_MESSAGE);
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Parsing Fault: Tracking index portion contains invalid integers.", "Conversion Failure", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        contentPane.add(rollNumber);
        
        bookTitle = new JTextField();
        bookTitle.setForeground(new Color(41, 97, 49));
        bookTitle.setColumns(10);
        bookTitle.setBounds(180, 184, 169, 20);
        contentPane.add(bookTitle);
        
        JComboBox bookCategory = new JComboBox();
        bookCategory.setModel(new DefaultComboBoxModel(new String[] {"Select Book", "Programming", "AI", "Database", "Networking", "Fantasy", "Science ", "Fiction", "Mystery", "Thriller", "Horror", "Historical ", "Fiction"}));
        bookCategory.setBounds(525, 181, 169, 22);
        contentPane.add(bookCategory);
        
        JDateChooser issueDateChooser = new JDateChooser();
        issueDateChooser.setBounds(180, 325, 169, 20);
        contentPane.add(issueDateChooser);
        
        JDateChooser returnDateChooser = new JDateChooser();
        returnDateChooser.setBounds(525, 325, 169, 20);
        contentPane.add(returnDateChooser);
        
        JLabel lblBookType = new JLabel("Book Type");
        lblBookType.setForeground(new Color(220, 220, 220));
        lblBookType.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblBookType.setBounds(221, 255, 98, 14);
        contentPane.add(lblBookType);
        
        JRadioButton rdbtnNewRadioButton = new JRadioButton("New Edition");
        rdbtnNewRadioButton.setBounds(325, 252, 109, 23);
        contentPane.add(rdbtnNewRadioButton);
        
        JRadioButton rdbtnPreloved = new JRadioButton("Old Edition");
        rdbtnPreloved.setBounds(436, 252, 109, 23);
        contentPane.add(rdbtnPreloved);
        
        ButtonGroup editionGroup = new ButtonGroup();
        editionGroup.add(rdbtnNewRadioButton);
        editionGroup.add(rdbtnPreloved);
        
        JLabel lblNewLabel_1 = new JLabel("Library Book Issue System");
        lblNewLabel_1.setForeground(new Color(0, 150, 255));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_1.setBounds(300, 66, 290, 14);
        contentPane.add(lblNewLabel_1);
        
        JButton btnNewButton = new JButton("Issue Book");
        btnNewButton.setBounds(364, 437, 109, 23);
        contentPane.add(btnNewButton);
        
        JButton btnReset = new JButton("Reset");
        btnReset.setBounds(192, 437, 109, 23);
        contentPane.add(btnReset);
        
        JButton btnNewButton_1 = new JButton("Exit");
        btnNewButton_1.setBounds(525, 437, 109, 23);
        contentPane.add(btnNewButton_1);
        
        // Button Action Process Handlers
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = studentName.getText().trim();
                    String rollStr = rollNumber.getText().trim();
                    String title = bookTitle.getText().trim();
                    
                    // 1. Guard check fields for EmptyFieldException
                    if (name.isEmpty() || rollStr.isEmpty() || title.isEmpty()) {
                        throw new EmptyFieldException("All text fields must be completely filled out!");
                    }
                    
                    // 2. Custom validation rule constraints for InvalidNameException
                    if (name.matches(".*\\d.*")) {
                        throw new InvalidNameException("Student Name cannot contain numerical digits!");
                    }
                    
                    // 3. Updated check: Enforces format compliance rules
                    if (!rollStr.matches("^[Ll]1\\s*[-]?\\s*[FSfs]\\d{2}\\s*[-]?\\s*[A-Za-z]+\\s*[-]?\\s*\\d{3,4}$")) {
                        throw new InvalidRollNumberException("Invalid Roll Number Format! Structure must follow campus rules.\n(e.g., L1 F23 BSSE 0377)");
                    }
                    
                    // 4. Verification isolates and parses trailing numeric characters safely
                    try {
                        String numericIdPart = rollStr.replaceAll("^.*?(?=\\d+$)", "");
                        Integer.parseInt(numericIdPart);
                    } catch (NumberFormatException nfe) {
                        throw new NumberFormatException("The tracking ID portion inside the Roll Number is invalid or overflows bounds!");
                    }
                    
                    // 5. Handling selections criteria for NullSelectionExceptions
                    if (bookCategory.getSelectedItem() == null || bookCategory.getSelectedItem().equals("Select Book")) {
                        throw new NullSelectionException("Please make a selection for the Book Category.");
                    }
                    if (!rdbtnNewRadioButton.isSelected() && !rdbtnPreloved.isSelected()) {
                        throw new NullSelectionException("Please make a selection for the Book Edition Type.");
                    }
                    if (issueDateChooser.getDate() == null || returnDateChooser.getDate() == null) {
                        throw new NullSelectionException("Both Issue Date and Return Date values must be chosen.");
                    }
                    
                    // 6. Chronological date evaluation checks for InvalidDateException
                    Date issueDate = issueDateChooser.getDate();
                    Date returnDate = returnDateChooser.getDate();
                    if (returnDate.before(issueDate)) {
                        throw new InvalidDateException("The Return Date cannot be chronologically earlier than the Issue Date.");
                    }
                    
                    // Success confirmation triggers when input blocks successfully pass evaluations
                    JOptionPane.showMessageDialog(null, "Book Issued Successfully to " + name + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                } catch (EmptyFieldException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Input Error", JOptionPane.WARNING_MESSAGE);
                } catch (InvalidNameException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Name Error", JOptionPane.ERROR_MESSAGE);
                } catch (InvalidRollNumberException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Roll Number Format Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Numeric parsing error: " + ex.getMessage(), "Conversion Error", JOptionPane.ERROR_MESSAGE);
                } catch (NullSelectionException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Selection Error", JOptionPane.WARNING_MESSAGE);
                } catch (InvalidDateException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Date Logical Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    System.out.println("Backend validation routine finished evaluating input.");
                }
            }
        });

        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                studentName.setText("");
                rollNumber.setText("");
                bookTitle.setText("");
                bookCategory.setSelectedIndex(0);
                issueDateChooser.setDate(null);
                returnDateChooser.setDate(null);
                editionGroup.clearSelection();
            }
        });

        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, 
                    "Are you sure you want to exit?", "Exit", 
                    JOptionPane.YES_NO_OPTION);
                    
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }
}