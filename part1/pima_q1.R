# loading data
train_data <- read.table("http://archive.ics.uci.edu/ml/machine-learning-databases/pima-indians-diabetes/pima-indians-diabetes.data",sep = ",")

#Assigning column names
colnames(train_data) <- c("No_of_times_pregnant","Plasma_glucose","D_bp","Triceps","Insulin","BMI","D_pf","Age","Class")

#summary of dataset
summary(train_data)
str(train_data)

# 1. Creating Hsitogram and barplots
#Histogram and barplot for variable No_of_times_pregrnant
hist(train_data$No_of_times_pregnant,main = "histogram of No_of_times_pregnant" ,prob=TRUE)
lines(density(train_data$No_of_times_pregnant))
counts <- table(train_data$No_of_times_pregnant)
barplot(counts, main="Bar Plot of No_of_times_pregnant")

#Histogram and barplot for variable Plasma_glucose_concentration
hist(train_data$Plasma_glucose,main="histogram of Plasma_glucose_concentration",prob=TRUE)
lines(density(train_data$Plasma_glucose))
counts2 <- table(train_data$Plasma_glucose)
barplot(counts2, main="Bar Plot of Plasma_glucose_concentration")

#Histogram and barplot for variable Diastolic_Blood_Pressure
hist(train_data$D_bp,main="histogram of Diastolic_Blood_Pressure",prob=TRUE)
lines(density(train_data$D_bp))
counts3 <- table(train_data$D_bp)
barplot(counts3, main="Bar Plot of Diastolic_Blood_Pressure")

#Histogram and barplot for variable Triceps_skin_fold_thickness
hist(train_data$Triceps,main="histogram of Triceps_skin_fold_thickness ",prob=TRUE)
lines(density(train_data$Triceps))
counts4 <- table(train_data$Triceps)
barplot(counts4, main="Bar Plot of Triceps_skin_fold_thickness")

#Histogram and barplot for variable 2_Hour_serum_insulin
hist(train_data$Insulin,main="histogram of 2_Hour_serum_insulin ",prob=TRUE)
lines(density(train_data$Insulin))
counts5 <- table(train_data$Insulin)
barplot(counts5, main="Bar Plot of 2_Hour_serum_insulin")

#Histogram and barplot for variable Body_mass_index 
hist(train_data$BMI,main="histogram of Body_mass_index",prob=TRUE)
lines(density(train_data$BMI))
counts6 <- table(train_data$BMI)
barplot(counts6, main="Bar Plot of Body_mass_index")

#Histogram and barplot for variable Diabetes_pedigree_function
hist(train_data$D_pf,main="histogram of Diabetes_pedigree_function ",prob=TRUE)
lines(density(train_data$D_pf))
counts7 <- table(train_data$D_pf)
barplot(counts7, main="Bar Plot of Diabetes_pedigree_function")

#Histogram and barplot for variable Age
hist(train_data$Age,main="histogram of Age",prob=TRUE)
lines(density(train_data$Age))
counts8 <- table(train_data$Age)
barplot(counts8, main="Bar Plot of Age")

# 2. Finding correlation between each of the attributes and the class_variable
class_variable <- as.numeric(train_data$Class)
for(i in 1:8){
  x <- train_data[i]
  output <- cor(x,class_variable)
  print(output)
}

# 3.Comouting the correlation between all pairs of 8 attributes and finding the highest mutual correlation
final_i <- 0
final_j <- 0
result <- 0
for(i in 1:8)
{
  for(j in 1:8)
  {
    if(i==j)
      next
    x <- train_data[i]
    y <- train_data[j]
    temp <- cor(x,y)
    if(temp > result){
      result <- temp
      final_i <- i
      final_j <- j
    }
  }
}
result <- cor(train_data[final_i], train_data[final_j])
result
