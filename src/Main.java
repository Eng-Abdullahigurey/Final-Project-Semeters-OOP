import javax.swing.*;
import java.awt.*;
import java.io.*;

// 1. Abstract Class
abstract class Person {
    private final int personID;
    private final String name;
    private final int age;
    private final String gender;
    private final String phoneNumber;
    private final String address;

    public Person(int personID, String name, int age, String gender, String phoneNumber, String address) {
        this.personID = personID;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int getPersonID() { return personID; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAddress() { return address; }

    public abstract String performRole();

    public String displayPersonDetails() {
        return "Person ID: " + personID +
                "\nName: " + name +
                "\nAge: " + age +
                "\nGender: " + gender +
                "\nPhone Number: " + phoneNumber +
                "\nAddress: " + address;
    }
}

// 2. Patient Class
class Patient extends Person {
    private final String patientId;
    private final String bloodGroup;
    private final String disease;
    private final String medicalHistory;
    private final String admissionDate;

    public Patient(int personID, String name, int age, String gender, String phoneNumber, String address,
                   String patientId, String bloodGroup, String disease, String medicalHistory, String admissionDate) {
        super(personID, name, age, gender, phoneNumber, address);
        this.patientId = patientId;
        this.bloodGroup = bloodGroup;
        this.disease = disease;
        this.medicalHistory = medicalHistory;
        this.admissionDate = admissionDate;
    }

    public String getPatientId() { return patientId; }
    public String getBloodGroup() { return bloodGroup; }
    public String getDisease() { return disease; }
    public String getMedicalHistory() { return medicalHistory; }
    public String getAdmissionDate() { return admissionDate; }

    @Override
    public String performRole() {
        return "Patient role: Receives treatment and keeps medical records.";
    }

    public String displayPatientDetails() {
        return displayPersonDetails() +
                "\nPatient ID: " + patientId +
                "\nBlood Group: " + bloodGroup +
                "\nDisease: " + disease +
                "\nMedical History: " + medicalHistory +
                "\nAdmission Date: " + admissionDate;
    }
}

// 3. Doctor Class
class Doctor extends Person {
    private final String doctorId;
    private final String specialization;
    private final String qualification;
    private final String experience;
    private final int consultationFee;

    public Doctor(int personID, String name, int age, String gender, String phoneNumber, String address,
                  String doctorId, String specialization, String qualification, String experience, int consultationFee) {
        super(personID, name, age, gender, phoneNumber, address);
        this.doctorId = doctorId;
        this.specialization = specialization;
        this.qualification = qualification;
        this.experience = experience;
        this.consultationFee = consultationFee;
    }

    public String getDoctorId() { return doctorId; }
    public String getSpecialization() { return specialization; }
    public String getQualification() { return qualification; }
    public String getExperience() { return experience; }
    public int getConsultationFee() { return consultationFee; }

    @Override
    public String performRole() {
        return "Doctor role: Checks patients, gives consultation, and manages treatment.";
    }

    public String displayDoctorDetails() {
        return displayPersonDetails() +
                "\nDoctor ID: " + doctorId +
                "\nSpecialization: " + specialization +
                "\nQualification: " + qualification +
                "\nExperience: " + experience +
                "\nConsultation Fee: " + consultationFee;
    }
}

// 4. Appointment Class
class Appointment {
    private final int appointmentId;
    private final String appointmentDate;
    private final String appointmentTime;
    private final boolean status;
    private final Patient patient;
    private final Doctor doctor;

    public Appointment(int appointmentId, String appointmentDate, String appointmentTime,
                       boolean status, Patient patient, Doctor doctor) {
        this.appointmentId = appointmentId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.patient = patient;
        this.doctor = doctor;
    }

    public int getAppointmentId() { return appointmentId; }
    public String getAppointmentDate() { return appointmentDate; }
    public String getAppointmentTime() { return appointmentTime; }

    public String displayAppointmentDetails() {
        return "Appointment ID: " + appointmentId +
                "\nAppointment Date: " + appointmentDate +
                "\nAppointment Time: " + appointmentTime +
                "\nStatus: " + (status ? "Confirmed" : "Pending") +
                "\nPatient Name: " + patient.getName() +
                "\nDoctor Name: " + doctor.getName() +
                "\nDoctor Specialization: " + doctor.getSpecialization();
    }
}

// 5. Payment Interface
interface Payment {
    String processPayment();
    String generateReceipt();
}

// 6. Card Payment
class CardPayment implements Payment {
    public String processPayment() {
        return "Processing card payment...\nCard details validated successfully.";
    }

    public String generateReceipt() {
        return "Digital receipt generated successfully.";
    }
}

// 7. Cash Payment
class CashPayment implements Payment {
    public String processPayment() {
        return "Processing cash payment...";
    }

    public String generateReceipt() {
        return "Cash payment receipt generated successfully.";
    }
}

// 8. Bill Class
class Bill {
    private final int billId;
    private final int consultationCharge;
    private final int laboratoryCharge;
    private final int medicalCharge;
    private final int totalAmount;
    private final Payment payment;

    public Bill(int billId, int consultationCharge, int laboratoryCharge, int medicalCharge, Payment payment) {
        this.billId = billId;
        this.consultationCharge = consultationCharge;
        this.laboratoryCharge = laboratoryCharge;
        this.medicalCharge = medicalCharge;
        this.payment = payment;
        this.totalAmount = calculateBill();
    }

    public int getBillId() { return billId; }
    public int getTotalAmount() { return totalAmount; }

    public int calculateBill() {
        return consultationCharge + laboratoryCharge + medicalCharge;
    }

    public String payBill() {
        return payment.processPayment() + "\n" + payment.generateReceipt();
    }

    public String displayBillDetails() {
        return "Bill ID: " + billId +
                "\nConsultation Charge: " + consultationCharge +
                "\nLaboratory Charge: " + laboratoryCharge +
                "\nMedical Charge: " + medicalCharge +
                "\nTotal Amount: " + totalAmount;
    }
}

// 9. File Manager
class FileManager {
    private final String fileName;

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    public void saveData(String data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            bw.write(data);
            bw.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving data: " + e.getMessage());
        }
    }

    public String readData() {
        StringBuilder records = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                records.append(line).append("\n");
            }

        } catch (FileNotFoundException e) {
            return "File not found. Please save data first.";
        } catch (IOException e) {
            return "Error reading data: " + e.getMessage();
        }

        return records.toString();
    }
}

// 10. GUI Main Class
public class Main extends JFrame {

    private final JTextArea outputArea;

    private Patient patient;
    private Doctor doctor;
    private Appointment appointment;
    private Bill bill;

    private final FileManager fileManager = new FileManager("MediCareRecords.txt");

    public Main() {
        setTitle("MediCare Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("MediCare Management System", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setForeground(new Color(0, 102, 153));

        JButton btnPatient = new JButton("Register Patient");
        JButton btnDoctor = new JButton("Add Doctor");
        JButton btnAppointment = new JButton("Schedule Appointment");
        JButton btnBill = new JButton("Generate Bill");
        JButton btnPayment = new JButton("Process Payment");
        JButton btnSave = new JButton("Save Records");
        JButton btnView = new JButton("View Records");
        JButton btnClear = new JButton("Clear");

        JPanel buttonPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        buttonPanel.add(btnPatient);
        buttonPanel.add(btnDoctor);
        buttonPanel.add(btnAppointment);
        buttonPanel.add(btnBill);
        buttonPanel.add(btnPayment);
        buttonPanel.add(btnSave);
        buttonPanel.add(btnView);
        buttonPanel.add(btnClear);

        outputArea = new JTextArea();
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        outputArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(title, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);

        btnPatient.addActionListener(e -> registerPatient());
        btnDoctor.addActionListener(e -> addDoctor());
        btnAppointment.addActionListener(e -> scheduleAppointment());
        btnBill.addActionListener(e -> generateBill());
        btnPayment.addActionListener(e -> processPayment());
        btnSave.addActionListener(e -> saveRecords());
        btnView.addActionListener(e -> viewRecords());
        btnClear.addActionListener(e -> outputArea.setText(""));
    }

    private void registerPatient() {
        patient = new Patient(
                101,
                "Hafsa",
                25,
                "Female",
                "031313799",
                "Doll02",
                "P001",
                "O+",
                "Fever",
                "Diabetes, High Blood Pressure",
                "11/07/2026"
        );

        outputArea.setText("========== PATIENT REGISTERED SUCCESSFULLY ==========\n");
        outputArea.append(patient.displayPatientDetails());
        outputArea.append("\n\n" + patient.performRole());
    }

    private void addDoctor() {
        doctor = new Doctor(
                102,
                "Dr Ahmed Abdullah",
                30,
                "Male",
                "02356771",
                "Somalia MQS",
                "D101",
                "Cardiologist",
                "PhD",
                "5 Years",
                4500
        );

        outputArea.setText("========== DOCTOR ADDED SUCCESSFULLY ==========\n");
        outputArea.append(doctor.displayDoctorDetails());
        outputArea.append("\n\n" + doctor.performRole());
    }

    private void scheduleAppointment() {
        if (patient == null || doctor == null) {
            JOptionPane.showMessageDialog(this, "Please register patient and add doctor first.");
            return;
        }

        appointment = new Appointment(
                1001,
                "11/07/2026",
                "11:30 PM",
                true,
                patient,
                doctor
        );

        outputArea.setText("========== APPOINTMENT SCHEDULED SUCCESSFULLY ==========\n");
        outputArea.append(appointment.displayAppointmentDetails());
    }

    private void generateBill() {
        Payment payment = new CardPayment();

        bill = new Bill(
                102,
                2000,
                1500,
                1000,
                payment
        );

        outputArea.setText("========== BILL GENERATED SUCCESSFULLY ==========\n");
        outputArea.append(bill.displayBillDetails());
    }

    private void processPayment() {
        if (bill == null) {
            JOptionPane.showMessageDialog(this, "Please generate bill first.");
            return;
        }

        outputArea.setText("========== PAYMENT PROCESS ==========\n");
        outputArea.append(bill.payBill());
    }

    private void saveRecords() {
        if (patient == null || doctor == null || appointment == null || bill == null) {
            JOptionPane.showMessageDialog(this, "Please complete all steps before saving records.");
            return;
        }

        String records =
                "\n========== PATIENT RECORD ==========\n" +
                        "Patient ID: " + patient.getPatientId() + "\n" +
                        "Patient Name: " + patient.getName() + "\n" +
                        "Disease: " + patient.getDisease() + "\n" +
                        "Blood Group: " + patient.getBloodGroup() + "\n" +

                        "\n========== DOCTOR RECORD ==========\n" +
                        "Doctor ID: " + doctor.getDoctorId() + "\n" +
                        "Doctor Name: " + doctor.getName() + "\n" +
                        "Specialization: " + doctor.getSpecialization() + "\n" +

                        "\n========== APPOINTMENT RECORD ==========\n" +
                        "Appointment ID: " + appointment.getAppointmentId() + "\n" +
                        "Appointment Date: " + appointment.getAppointmentDate() + "\n" +
                        "Appointment Time: " + appointment.getAppointmentTime() + "\n" +

                        "\n========== BILL RECORD ==========\n" +
                        "Bill ID: " + bill.getBillId() + "\n" +
                        "Total Amount: " + bill.getTotalAmount() + "\n" +

                        "\n========== PAYMENT RECORD ==========\n" +
                        "Payment Method: Card Payment\n" +
                        "=====================================\n";

        fileManager.saveData(records);

        JOptionPane.showMessageDialog(this, "Records saved successfully.");
        outputArea.setText(records);
    }

    private void viewRecords() {
        outputArea.setText("========== SAVED HOSPITAL RECORDS ==========\n\n");
        outputArea.append(fileManager.readData());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}