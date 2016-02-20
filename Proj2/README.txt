Name:Shashank Adidamu
netid: sxa145730

R package rpart was used.

command to run the code:  

Rscript DT.R training_set.csv validation_set.csv test_set.csv 

for dataset1
—————————————
R script ——— DT.R

To find a good pruning model 6 different values of CP were considered:

The first pruned model(pruned_model1) was built with CP value equal to (cp value corresponding to minimum xerror) and the accuracy obtained was 74.45%

The second pruned model(pruned_model2) was built with CP value equal to (cp value corresponding to maximum xerror) and the accuracy obtained was 50%

The third pruned model(pruned_model3) was built with CP value equal to mean of all the cp_values and the accuracy obtained was 60.3%

The fourth pruned model(pruned_model4) was built with CP value equal to median of the cp_values and the accuracy obtained was 65.3%

The fifth pruned model(pruned_model5) was built with CP value equal to 25th quantile among cp_values and the accuracy obtained was 73.35%

The sixth pruned model(pruned_model6) was built with CP value equal to 75th quantile among cp_values and the accuracy obtained was 63.45%

After looking at the accuracies obtained by building different pruned models, the CP value corresponding to minimum Xerror is considered to be giving good predictions on test data.


for dataset2
—————————————

command to run the code:  

Rscript DT.R training_set.csv validation_set.csv test_set.csv 


To find a good pruning model 6 different values of CP were considered:

The first pruned model(pruned_model1) was built with CP value equal to (cp value corresponding to minimum xerror) and the accuracy obtained was 72.66%

The second pruned model(pruned_model2) was built with CP value equal to (cp value corresponding to maximum xerror) and the accuracy obtained was 50%

The third pruned model(pruned_model3) was built with CP value equal to mean of all the cp_values and the accuracy obtained was 61%

The fourth pruned model(pruned_model4) was built with CP value equal to median of the cp_values and the accuracy obtained was 67%

The fifth pruned model(pruned_model5) was built with CP value equal to 25th quantile among cp_values and the accuracy obtained was 69.83%

The sixth pruned model(pruned_model6) was built with CP value equal to 75th quantile among cp_values and the accuracy obtained was 61%

After looking at the accuracies obtained by building different pruned models, the CP value corresponding to minimum Xerror is considered to be giving good predictions on test data.

