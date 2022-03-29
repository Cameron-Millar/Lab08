package com.example.lab08;


public class StudentRecord {
    String StudentID;
    double Midterm;
    double Assignments;
    double FinalExam;
    double FinalMark;
    String LetterGrade;


    public StudentRecord(String StudentID, double Assignments, double Midterm, double FinalExam){
        this.StudentID = StudentID;
        this.Midterm = Midterm;
        this.Assignments = Assignments;
        this.FinalExam = FinalExam;
        FinalMark = (Assignments*0.2)+(Midterm*0.3)+(FinalExam*0.5);
        if (FinalMark >= 80){
            LetterGrade = "A";
        }
        else if (FinalMark >= 70){
            LetterGrade = "B";
        }
        else if (FinalMark >= 60){
            LetterGrade = "C";
        }
        else if (FinalMark >= 50){
            LetterGrade = "D";
        }
        else{
            LetterGrade = "F";
        }
    }
    public String getStudentID(){
        return StudentID;
    }
    public double getMidterm(){
        return Midterm;
    }
    public double getAssignments(){
        return Assignments;
    }
    public double getFinalExam(){
        return FinalExam;
    }
    public double getFinalMark(){
        return FinalMark;
    }

    public String getLetterGrade() {
        return LetterGrade;
    }
}
