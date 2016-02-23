

library(rpart)
library(e1071) 
library(class)
library("neuralnet")
library(mlbench)
library(ada)
library(randomForest)
library(neuralnet)
library(nnet)
library(ipred)

args <- commandArgs(TRUE)
dataURL<-as.character(args[1])
header<-as.logical(args[2])
d<-read.csv(dataURL,header = header)

set.seed(123)
value <- as.integer(args[3])

# create 10 samples
for(i in 1:10) {
  
  #---DecisionTree----
  
  method="Decision Tree" 
  cat("Running sample ",i,"\n")
  
  sampleInstances<-sample(1:nrow(d),size = 0.9*nrow(d))
  trainingData<-d[sampleInstances,]
  trainingData <-na.omit(trainingData)
  testData<-d[-sampleInstances,]
  testData <-na.omit(testData)
  # which one is the class attribute
  Class<-as.factor(as.integer(args[3]))
  # now create all the classifiers and output accuracy values:
  
  Class2<-testData[,as.integer(value)]
  # now create all the classifiers and output accuracy values:

  model <- rpart(as.formula(paste0("as.factor(", colnames(d)[value], ") ~ .")),data=trainingData,method="class",parms = list(split = 'information'),minsplit = 2, minbucket = 1)
  
  pruned_model1<- prune(model, cp=model$cptable[which.min(model$cptable[,"xerror"]),"CP"])
  
  predicted_values<-predict(pruned_model1,testData,type="class")
  
  original_values <- Class2
  
  original_values
  
  a <- cbind(predicted_values,original_values)
  
  correct_predictions <- length(a[predicted_values==original_values])/2
  
  total_predictions <- length(a)/2
  
  accuracy <- (correct_predictions/total_predictions)*100
  
  cat("Method = ", method,", accuracy= ", accuracy,"\n")
  
 #------SVM--------
  method = "SVM"
  
  svm.model <- svm(as.formula(paste0("as.factor(", colnames(d)[value], ") ~ .")), data=trainingData)
  svm_predicted_values <- predict(svm.model, testData)
  
  
  a_svm <- cbind(svm_predicted_values,original_values)
  
  svm_correct_predictions <- length(a_svm[svm_predicted_values==original_values])/2
  
  svm_total_predictions <- length(a_svm)/2
  
  svm_accuracy <- (svm_correct_predictions/svm_total_predictions)*100
  
  cat("Method = ", method,", accuracy= ", svm_accuracy,"\n")
  

  #--naivebayes---
  
  method <- 'NaiveBayes'
  nb.model <- naiveBayes(as.formula(paste0("as.factor(", colnames(d)[value], ") ~ .")), data = trainingData)
  nb_predicted_values <- predict(nb.model, testData, type = "class")
  
  a_nb <- cbind(nb_predicted_values,original_values)
  
  nb_correct_predictions <- length(a_nb[nb_predicted_values==original_values])/2
  
  nb_total_predictions <- length(a_nb)/2
  
  nb_accuracy <- (nb_correct_predictions/nb_total_predictions)*100
  
  cat("Method = ", method,", accuracy= ", nb_accuracy,"\n")
  
  #--Logistic Reg---
  method <- 'Logistic Regression'
  lr.model <- glm(as.formula(paste0("as.factor(", colnames(d)[value], ") ~ .")), data = trainingData, family = "binomial")
  predlogistic<-predict(lr.model, newdata=testData, type="response")
  threshold=0.65
  lr_predicted_values<-sapply(predlogistic, FUN=function(x) if (x>threshold) 1 else 0)
  
  a_lr <- cbind(lr_predicted_values,original_values)
  
  lr_correct_predictions <- length(a_lr[lr_predicted_values==original_values])/2
  
  lr_total_predictions <- length(a_lr)/2
  
  lr_accuracy <- (lr_correct_predictions/lr_total_predictions)*100
  
  cat("Method = ", method,", accuracy= ", lr_accuracy,"\n")
  
  #--randomforest--
  
  method <- "random"
  rfModel <- randomForest(as.formula(paste0("as.factor(", colnames(d)[value], ") ~ .")), data=trainingData, importance=TRUE, proximity=TRUE, ntree=500)
  RF_predicted_values <- predict(rfModel,testData,type='response')
  
  a_rf <- cbind(RF_predicted_values,original_values)
  
  RF_correct_predictions <- length(a_rf[RF_predicted_values==original_values])/2
  
  RF_total_predictions <- length(a_rf)/2
  
  RF_accuracy <- (RF_correct_predictions/RF_total_predictions)*100
  
  cat("Method = ", method,", accuracy= ", RF_accuracy,"\n")
  
  #----nn--
  method <- 'neural'
  nn.model <- nnet(as.formula(paste0("as.factor(", colnames(d)[value], ") ~ .")), trainingData,size=1)
  nn_predicted_values <- predict(nn.model, testData)
  a_nn <- cbind(nn_predicted_values,original_values)
  
  nn_correct_predictions <- length(a_nn[nn_predicted_values==original_values])/2
  
  nn_total_predictions <- length(a_nn)/2
  
  nn_accuracy <- (nn_correct_predictions/nn_total_predictions)*100
  
  cat("Method = ", method,", accuracy= ", nn_accuracy,"\n")
  
  #Boosting
  method <- 'boosting'
  boosting.model <- ada(as.formula(paste0("as.factor(", colnames(d)[value], ") ~ .")), data = trainingData, iter=20, nu=1, type="discrete")
  boosting_predicted_values=predict(boosting.model,testData)
  
  a_boost <- cbind(boosting_predicted_values,original_values)
  
  boosting_correct_predictions <- length(a_boost[boosting_predicted_values==original_values])/2
  
  boosting_total_predictions <- length(a_boost)/2
  
  boosting_accuracy <- (boosting_correct_predictions/boosting_total_predictions)*100
  
  cat("Method = ", method,", accuracy= ", nb_accuracy,"\n")
  

 
  
  
  
 
  
}
