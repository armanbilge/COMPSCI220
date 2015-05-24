"""
dfs.py
Arman Bilge
8079403
COMPSCI 220
Assignment 4
Part 3
"""

with open('list.txt') as f:
    graph = [list(map(int, l.split(','))) for l in f]
with open('dfs.txt', 'w') as f:
    visited = [False] * len(graph)
    counter = iter(range(len(graph)))
    stack = []
    while len(stack) > 0 or not all(visited):
        if len(stack) == 0:
            stack.append(next(i for i, v in enumerate(visited) if not v))
        node = stack[-1]
        visited[node] = True
        if any(not visited[i] for i in graph[node]):
            stack.append(next(i for i in graph[node] if not visited[i]))
        else:
            stack.pop()
            print('{},{}'.format(next(counter), node), file=f)
        if len(stack) == 0:
            print(file=f)
