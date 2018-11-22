# Javascript Notes

The evaluator doesn't validate inputs - if it was passed ['p', undefined, '^'] it would return NaN - but this was a deliberate decision. The evaluator isn't the public interface for the module and it is could be called several million times for 6 input numbers, so validation is done futher upstream.

There is currently no optimsation for duplicates. Given a target of 5 and inputs of [2,2] the process would try
2(first) 2(second) + \
2(first) 2(second) * \
2(first) 2(second) - \
2(first) 2(second) / \
2(second) 2(first) + \
2(second) 2(first) * \
2(second) 2(first) - \
2(second) 2(first) /

The Pattern currently doesn't store intermediate evaluations when extended - all patterns are evaluated 'from scratch'. For example, when a Pattern of [2 3 +] is evaluated as 5, it _could_ store that internally, so that if it was further extended to [2 3 + 4 +] it could use the intermediate 5 to just evaluate that as [5 4 +]
