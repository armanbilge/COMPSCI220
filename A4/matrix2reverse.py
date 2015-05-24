"""
matrix2reverse.py
Arman Bilge
8079403
COMPSCI 220
Assignment 4
Part 2
"""

with open('matrix.txt') as f1, open('reverselist.txt', 'w') as f2:
    for c in zip(*(map(int, l.split(',')) for l in f1)):
        print(','.join(str(i) for i, a in enumerate(c) if a), file=f2)
