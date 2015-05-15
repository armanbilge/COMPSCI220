# matrix2list.py
# Arman Bilge
# 8079403
# COMPSCI 220
# Assignment 4
# Part 1

with open('matrix.txt') as f:
    with open('list.txt', 'w') as o:
        for l in f:
            print(','.join(str(i) for i, a in enumerate(map(int, l.split(','))) if a), file=o)
