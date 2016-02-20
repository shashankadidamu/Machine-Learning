library(rpart)
args <- commandArgs(TRUE) 

#file path
#file1 training dataset path, file2 test dataset path
#file1 <- "/Users/shashankadidamu/Desktop/assignment_2/data_sets1/training_set.csv"
#file2 <- "/Users/shashankadidamu/Desktop/assignment_2/data_sets1/test_set.csv"
#loading training data, test data
train_data <- read.csv(file=args[1],header = TRUE, sep = ",")
validation_data <- read.csv(file=args[2],header = TRUE ,sep =",")
test_data <- read.csv(file=args[3],header = TRUE,sep = ",")

#creating a decision tree model
model <- rpart(Class~.,data=train_data,method="class",parms = list(split = 'information'),minsplit = 2,minbucket = 1)

#plotting the model
par(mar=rep(0.1,4))

plot(model)

text(model)

printcp(model)

#storing the CP values for cptable
new_table <- model$cptable
cp_values <- new_table[,'CP']

#printing summary of model
summary(model)

#building pruned_model based on minimum xerror
pruned_model1<- prune(model, cp=model$cptable[which.min(model$cptable[,"xerror"]),"CP"])

#plotting the pruned model
par(mar=rep(0.1,4))

plot(pruned_model1)

text(pruned_model1)

#predicted_values
predicted_values<-predict(pruned_model1,test_data,type="class")

#original_values
original_values <- test_data$Class
a <- cbind(predicted_values,original_values)

#total number of correct_predictions
correct_predictions <- length(a[predicted_values==original_values])/2

#total number of predictions made
total_predictions <- length(a)/2

#calculating accuracy
accuracy <- (correct_predictions/total_predictions)*100
accuracy


#building pruned_model based on maximum xerror
pruned_model2<- prune(model, cp=model$cptable[which.max(model$cptable[,"xerror"]),"CP"])

#plotting the pruned model
#par(mar=rep(0.1,4))

#plot(pruned_model2)

#text(pruned_model2)

#predicted_values
predicted_values2<-predict(pruned_model2,test_data,type="class")

#original_values
original_values2 <- test_data$Class
b <- cbind(predicted_values2,original_values2)

#number of correct_predictions
correct_predictions2 <- length(b[predicted_values2==original_values2])/2

#number of total_predictions
total_predictions2 <- length(b)/2

#accuracy
accuracy2 <- (correct_predictions2/total_predictions2)*100
accuracy2

#building pruned_model based on mean of cp_values obtained from cptable
pruned_model3<- prune(model, cp=mean(cp_values))

#plotting the pruned model
par(mar=rep(0.1,4))

plot(pruned_model3)

text(pruned_model3)

#predicted_values
predicted_values3<-predict(pruned_model3,test_data,type="class")

#original_values
original_values3 <- test_data$Class
c <- cbind(predicted_values3,original_values3)

#number of correct_predictions
correct_predictions3 <- length(c[predicted_values3==original_values3])/2

#number of total_predictions
total_predictions3 <- length(c)/2

#accuracy
accuracy3 <- (correct_predictions3/total_predictions3)*100
accuracy3

#building pruned_model based on median of cp_values obtained from cptable
pruned_model4<- prune(model, cp=median(cp_values))

#plotting the pruned model
par(mar=rep(0.1,4))

plot(pruned_model4)

text(pruned_model4)

#predicted_values
predicted_values4<-predict(pruned_model4,test_data,type="class")

#original_values
original_values4 <- test_data$Class
d <- cbind(predicted_values4,original_values4)

#number of correct_predictions
correct_predictions4 <- length(d[predicted_values4==original_values4])/2

#number of total_predictions
total_predictions4 <- length(d)/2

#accuracy
accuracy4 <- (correct_predictions4/total_predictions4)*100
accuracy4

#building pruned model based on 25th quantile of cp_values
pruned_model5<- prune(model, cp=quantile(cp_values,c(.25)))

#plotting pruned_model
plot(pruned_model5)
text(pruned_model5)

#predicted_values
predicted_values5<-predict(pruned_model5,test_data,type="class")

#original_values
original_values5 <- test_data$Class

e <- cbind(predicted_values5,original_values5)

#total no of correct predictions
correct_predictions5 <- length(e[predicted_values5==original_values5])/2

#total predictions made
total_predictions5 <- length(e)/2

#accuracy
accuracy5 <- (correct_predictions5/total_predictions5)*100
accuracy5

#building pruned model based on 75th quantile cp_values
pruned_model6<- prune(model, cp=quantile(cp_values,c(.75)))

#plotting pruned model
plot(pruned_model6)
text(pruned_model6)

#predicted_values
predicted_values6<-predict(pruned_model6,test_data,type="class")

#original_values
original_values6<- test_data$Class
f <- cbind(predicted_values6,original_values6)

#total number of correct predictions
correct_predictions6 <- length(f[predicted_values6==original_values6])/2

#total number of total predictions
total_predictions6 <- length(f)/2

#accuracy
accuracy6 <- (correct_predictions6/total_predictions6)*100
accuracy6
