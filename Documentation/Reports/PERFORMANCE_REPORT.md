=== SMART E-COMMERCE PERFORMANCE REPORT ===
Generated: Mon Apr 27 10:55:10 CAT 2026

SCENARIO 1: NO INDEXES / NO CACHE
---------------------------------
Q1: Search 'laptop'            : 1.61 ms (avg of 5 runs)
Q2: Join Products/Categories   : 1.52 ms (avg of 5 runs)
Q3: Filter Category 1          : 0.82 ms (avg of 5 runs)

SCENARIO 2: INDEXES ONLY (NO CACHE)
-----------------------------------
Q1: Search 'laptop'            : 0.86 ms (avg of 5 runs)
Q2: Join Products/Categories   : 1.29 ms (avg of 5 runs)
Q3: Filter Category 1          : 0.97 ms (avg of 5 runs)

SCENARIO 3: INDEXES + CACHE
---------------------------
Q1: Search 'laptop'            : 1.13 ms (avg of 5 runs)
Q2: Join Products/Categories   : 1.31 ms (avg of 5 runs)
Q3: Filter Category 1          : 0.82 ms (avg of 5 runs)

=== PERFORMANCE ANALYSIS ===
Q1: Search 'laptop':
  Index Improvement: 46.4%
  Overall (Index+Cache) Improvement: 30.0%
Q2: Join Products/Categories:
  Index Improvement: 15.0%
  Overall (Index+Cache) Improvement: 13.7%
Q3: Filter Category 1:
  Index Improvement: -18.0%
  Overall (Index+Cache) Improvement: 0.1%

Cache Hit Rate during Scenario 3: 80% (Simulated: 4 of 5 runs)
