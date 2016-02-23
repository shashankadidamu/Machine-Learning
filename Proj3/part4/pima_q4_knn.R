library (class)
sum =0
count =0
val_k=3
cat("k",":","Average Accuracy")
for(i in 1:5){
  for(i in 1:10){
    # loading data
    pima_data <- read.table("http://archive.ics.uci.edu/ml/machine-learning-databases/pima-indians-diabetes/pima-indians-diabetes.data",sep = ",")
    
    #Assigning column names
    colnames(pima_data) <- c("No_of_times_pregnant","Plasma_glucose","D_bp","Triceps","Insulin","BMI","D_pf","Age","Class")
    
    pima_data$Class<- factor(pima_data$Class)
    
    noofrows<- nrow(pima_data)
    train <- sort(sample(1:noofrows, floor(0.90*(noofrows))))
    #Collecting 90% of data into training data
    pima_data_train <- pima_data[train,]
    #Collecting 10% of data into testing data
    pima_data_test <- pima_data[-train,]
    
    cl = factor(pima_data_train$Class)
    cl_test = factor(pima_data_test$Class)
    knn_classifier <- knn(pima_data_train, pima_data_test, cl, k = val_k, prob=TRUE) 
    
    original_values <- pima_data_test$Class
    b <- cbind(knn_classifier,original_values)
    correct_predictions <- length(b[knn_classifier==original_values])/2
    #print(correct_predictions)
    total_predictions <- length(b)/2
    #print(total_predictions)
    accuracy <- (correct_predictions/total_predictions)*100
    #print(accuracy)
    sum=sum+accuracy
    count=count+1
  }
  average = sum/count
  cat(val_k,":",average,"\n")
  
  val_k=val_k+2
}

