"""
findstreets.py
Arman Bilge
8079403
COMPSCI 220
Assignment 4
Part 5
"""

with open('list.txt') as f:
    graph = [list(map(int, l.split(','))) for l in f]
with open('components.txt') as f:
    components = [list(map(int, l.split(','))) for l in f]

reduced = [[j for j, b in enumerate(components) if a != b and any(any(x in b for x in graph[k]) for k in a)] for i, a in enumerate(components)]
sinks = [i for i, a in enumerate(reduced) if len(a) == 0]
reduced_reversed = [[j for j, a in enumerate(reduced) if i in a] for i in range(len(reduced))]
sources = [i for i, a in enumerate(reduced_reversed) if len(a) == 0]

with open('newstreets.txt', 'w') as f:
    print(8079403, file=f)
    for i, j in zip(sinks, sources):
        print('{},{}'.format(components[i][0], components[j][0]), file=f)
