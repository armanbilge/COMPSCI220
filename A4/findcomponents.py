"""
findcomponents.py
Arman Bilge
8079403
COMPSCI 220
Assignment 4
Part 4
"""

with open('reverselist.txt') as f:
    graph = [list(map(int, l.split(','))) for l in f]
with open('dfs.txt') as f:
    priorities = [int(l.split(',')[1]) for l in f if l.strip()][::-1]
with open('components.txt', 'w') as f:
    visited = [False] * len(graph)
    stack = []
    strong = []
    while len(stack) > 0 or not all(visited):
        if len(stack) == 0:
            stack.append(next(i for i in priorities if not visited[i]))
        node = stack[-1]
        visited[node] = True
        if any(not visited[i] for i in graph[node]):
            stack.append(next(i for i in graph[node] if not visited[i]))
        else:
            stack.pop()
            strong.append(node)
        if len(stack) == 0:
            print(','.join(map(str, sorted(strong))), file=f)
            strong.clear()
