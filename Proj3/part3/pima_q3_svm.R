library(e1071)
sum=0
count=0
for(i in 1:10){
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
  
  svm.model <- svm(Class~., data=pima_data_train)
  
  predicted_values <- predict(svm.model, pima_data_test)
  
  predicted_values
  
  original_values <- pima_data_test$Class
  b <- cbind(predicted_values,original_values)
  correct_predictions <- length(b[predicted_values==original_values])/2
  #print(correct_predictions)
  total_predictions <- length(b)/2
  #print(total_predictions)
  accuracy <- (correct_predictions/total_predictions)*100
  cat(i,":",accuracy,"\n")
  sum=sum+accuracy
  count=count+1
}
average = sum/count
cat("Overall Average",average)

kernal_values <-  c("Linear","Polynomial","Radial","Sigmoid")
for(i in kernal_values){
  for(j in 1:10){
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
    
    svm.model <- svm(Class~., data=pima_data_train, kernal=i)
    
    predicted_values <- predict(svm.model, pima_data_test)
    
    predicted_values
    
    original_values <- pima_data_test$Class
    b <- cbind(predicted_values,original_values)
    correct_predictions <- length(b[predicted_values==original_values])/2
    #print(correct_predictions)
    total_predictions <- length(b)/2
    #print(total_predictions)
    accuracy <- (correct_predictions/total_predictions)*100
    #cat(i,":",accuracy,"\n")
    sum=sum+accuracy
    count=count+1
  }
  average = sum/count
  cat(i,"Average Accuracy",average,"\n")
}


