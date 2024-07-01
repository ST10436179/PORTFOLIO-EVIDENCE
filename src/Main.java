import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;



public class Main {
    public static void main(String[] args) {
        String[] developerNames;
        String[] taskNames;
        String[] tasksStatus;
        int[] taskID;
        int[] tasksDurations;

        Login user = new Login();
        // GET USER NAME
        user.firstName = JOptionPane.showInputDialog("Enter Your Your First Name");
        // GET USER SURNAME
        user.lastName = JOptionPane.showInputDialog("Enter Your Your Last Name");

        // ENTER USERNAME
        do{
            user.username = JOptionPane.showInputDialog("Enter Username: Username should be \n no more  than 5" +
                    " characters and\n contain an underscore(_) ");
            user.isValidUser = user.checkUserName(user.username);
            if(!user.isValidUser){
                JOptionPane.showMessageDialog(null,"Username is not correctly formatted, please ensure \n that your username contains an underscore\n  an is more than 5 characters in length");
            }else{
                JOptionPane.showMessageDialog(null,"Username successfully captured");
            }
        }while(!user.isValidUser);

        // ENTER PASSWORD
        do{
            user.password = JOptionPane.showInputDialog("""
                    Enter Password: Your Password Should More Than\s
                     8 Characters Long, Contain A Capital
                     Letter,Contain A Number,
                     And Also Contain A Special Character""");
            user.isValidPassword = user.checkPasswordComplexity(user.password);
            if(!user.isValidPassword){
                String message = "Password is not correctly formatted, please ensure that your password contains  at least 8 " +
                        " characters, a capital letter, a number and a special character";
                JOptionPane.showMessageDialog(null,message);
            }else{
                JOptionPane.showMessageDialog(null,"Password successfully captured");
            }
        }while(!user.isValidPassword);

        JOptionPane.showMessageDialog(null,user.registerUser(user.username,user.password));
//         USER LOG IN
        JOptionPane.showMessageDialog(null,"You Can Now Login Using The Same Credentials");
        do{
            String loginUsername = JOptionPane.showInputDialog("Login:\n USERNAME Credentials");
            String loginPassword = JOptionPane.showInputDialog("Login:\n PASSWORD Credentials");
            user.isLoginIn =  user.loginUser(loginUsername,loginPassword,user.username,user.password);
            if(!user.isLoginIn){
                JOptionPane.showMessageDialog(null,"Your login details do not match try again");
            }

        }while(!user.isLoginIn);

        user.returnLoginStatus(user.isLoginIn, user.firstName, user.lastName);


        // Task Add
        Task task = new Task();

        task.numTasks = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of tasks:"));
        taskNames = new String[task.numTasks];
        developerNames = new String[task.numTasks];
        tasksStatus = new String[task.numTasks];
        taskID = new int[task.numTasks];
        tasksDurations = new int[task.numTasks];

        for(int i = 1; i <= task.numTasks; i++) {
            task.taskName = JOptionPane.showInputDialog("Enter task name:");
            taskNames[i-1] = task.taskName;
            task.taskDescription = JOptionPane.showInputDialog("Enter task description (max 50 characters):");
            while (!task.checkTaskDescription(task.taskDescription)) {
                task.taskDescription = JOptionPane.showInputDialog("Task description too long. Enter again (max 50 characters):");
            }


            task.developerDetails = JOptionPane.showInputDialog("Enter developer details:");
            developerNames[i-1] = task.developerDetails;
            int taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter task duration (hours):"));
            ///
            tasksDurations[i-1] = taskDuration;
            task.totalHours += taskDuration;
            task.taskID = task.createTaskID(i);
            taskID[i-1] = Integer.parseInt(task.taskID.split(": ")[1]);

            String[] statusOptions = {"To Do", "Doing", "Done"};
            int statusIndex = JOptionPane.showOptionDialog(null, "Select task status:", "Task Status",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, statusOptions, statusOptions[0]);
            String taskStatus = statusOptions[statusIndex];

            tasksStatus[i-1] = taskStatus;

            String taskDetails = task.printTaskDetails(task.taskName, i, task.taskDescription, task.developerDetails, taskDuration, task.taskID, taskStatus);
            JOptionPane.showMessageDialog(null, taskDetails);

        }
        JOptionPane.showMessageDialog(null, "Total combined hours for all tasks: " + task.returnTotalHours(task.totalHours));

        // SEARCH TASK NAME

        //GET USER TASK NAME
        String userInput = JOptionPane.showInputDialog("Search For A Task: Enter The Task Name");
        searchByTask(userInput,taskNames,developerNames,tasksStatus);
        // GET TASK END

        /// SEARCH BY DEVELOPER NAME
        String developer = JOptionPane.showInputDialog("Search For A Task By Developer Details");
        searchByDeveloper(developer,developerNames,tasksStatus,taskNames);
        //SEARCH END

        //DELETE TASK
        String userTask = JOptionPane.showInputDialog("Task You Wish To Delete");
        // CALLING THE DELETE TASK METHOD AND ALSO COPY OF THE NEW ARRAY
        String[] taskNames2 = deleteTask(userTask,taskNames);
        // DELETE END

        //DISPLAY REPORTS
        displayReports(taskNames2,developerNames,tasksStatus,taskID,tasksDurations);
        // DISPLAY END
    }


    // ARRAY SEARCH
    public static void searchByTask(String userInput,String[] taskNames,String[] developerNames, String[] tasksStatus){
        // CONVERTING INPUT TO LOWERCASE
        String user2 = userInput.toLowerCase();

        //LOOP THROUGH THE ARRAY
        for(int i=0;i< taskNames.length ;i++){
            if(user2.equals(taskNames[i].toLowerCase())){

                String output = "TASK NAME: " + taskNames[i] + "\n " +"DEVELOPER DETAILS: "+ developerNames[i] +
                        "\n "+"TASK STATUS: " + tasksStatus[i];
                //DISPLAY THE RESULTS
                JOptionPane.showMessageDialog(null,output);
                break;
            }
        }
    }
    //SEARCH BY DEVELOPER NAME
    public static void searchByDeveloper(String developer,String[] developerNames,String[] tasksStatus,String[] taskNames){
        String developer2 = developer.toLowerCase();

        //LOOP THROUGH THE ARRAY
        for(int i=0;i< developerNames.length ;i++){
            if(developer2.equals(developerNames[i].toLowerCase())){

                String output = "TASK NAME: " + taskNames[i]  +
                        "\n "+"TASK STATUS: " + tasksStatus[i];
                //DISPLAY THE RESULTS
                JOptionPane.showMessageDialog(null,output);
                break;
            }
        }
    }
    // DELETE TASK
    public static String[] deleteTask(String singleTask, String[] taskNames) {
        String task = singleTask.toLowerCase();

        int indexToRemove = -1;

        for (int i = 0; i < taskNames.length; i++) {
            if (task.equals(taskNames[i].toLowerCase())) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove != -1) {
            ArrayList<String> list = new ArrayList<>(Arrays.asList(taskNames));
            list.remove(indexToRemove);
            JOptionPane.showMessageDialog(null,"Task Deleted From The Array.");
            return list.toArray(new String[0]); // Return the updated array
        } else {
            return taskNames; // Return the original array if no task is found
        }
    }

    public static void displayReports(String[] taskNames,String[] developerNames, String[] tasksStatus,int[] taskID,int[] tasksDurations){
        for(int i=0;i<taskNames.length ; i++){
            String details ="TASKS REPORTS" + "\n" + "Task Names : "+ taskNames[i] + "\n"
                    + "Developer Names : "+ developerNames[i] + "\n" +
                     "Task Status : " + tasksStatus[i] + "\n" + "taskID : " + taskID[i]
                    + "\n" +  "Task Duration : " + tasksDurations[i];

            JOptionPane.showMessageDialog(null,details);
        }
    }

}