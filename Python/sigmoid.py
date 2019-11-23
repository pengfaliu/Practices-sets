import numpy as np
import matplotlib.pyplot as plt
def sigmoid(x):
    return 1.0/(1+np.exp(-x))

def test1(k):
    return 3*k+1+np.exp(-k)+np.exp(k)

sigmoid_inputs = np.arange(-10,10,0.1)
sigmoid_outputs = sigmoid(sigmoid_inputs)
print("Sigmoid Function Input :: {}".format(sigmoid_inputs))
print("Sigmoid Function Output :: {}".format(sigmoid_outputs))

plt.title("Sigmoid 函数")
plt.plot(sigmoid_inputs,sigmoid_outputs)
plt.xlabel("Sigmoid Inputs")
plt.ylabel("Sigmoid Outputs")
plt.show()


test1_inputs = np.arange(-10,10,0.1)
test1_outputs = test1(test1_inputs)


print("text1 Function Input :: {}".format(test1_inputs))
print("text1 Function Output :: {}".format(test1_outputs))

plt.plot(test1_inputs,test1_outputs)
plt.xlabel("test1_inputs")
plt.ylabel("test1_outputs")
plt.show()