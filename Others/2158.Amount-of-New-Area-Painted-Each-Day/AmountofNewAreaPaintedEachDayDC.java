public class AmountofNewAreaPaintedEachDayDC {
    public int[] amountPainted(int[][] paint) {
        List<Info> paints = mergePaint(paint, 0, paint.length - 1);
        
        int[] ans = new int[paint.length];
        for (Info info : paints) {
            ans[info.id] += info.end - info.start;
        }
        return ans;
    }
    
    private List<Info> mergePaint(int[][] paint, int start, int end) {
        if (start == end) {
            int[] p = paint[start];
            return List.of(new Info(start, p[0], p[1]));
        }
        
        int mid = start + (end - start)/2;
        List<Info> left = mergePaint(paint, start, mid);
        List<Info> right = mergePaint(paint, mid + 1, end);
        
        return merge(left, right);
    }
    
    private List<Info> merge(List<Info> left, List<Info> right) {
        Iterator<Info> lit = left.iterator();
        Iterator<Info> rit = right.iterator();
        Info l = lit.next();
        Info r = rit.next();
        List<Info> ans = new LinkedList<>();
        
        while ((l != null || lit.hasNext()) && (r != null || rit.hasNext())) {
            if (l == null) {
                l = lit.next();
            }
            if (r == null) {
                r = rit.next();
            }
            
            if (l.end <= r.start) {
                addToInfoList(ans, l);
                l = null;
                continue;
            }
            
            if (r.end <= l.start) {
                addToInfoList(ans, r);
                r = null;
                continue;
            }
            
            if (l.start < r.start) {
                addToInfoList(ans, new Info(l.id, l.start, r.start));
                l = new Info(l.id, r.start, l.end);
            } else if (r.start < l.start) {
                addToInfoList(ans, new Info(r.id, r.start, l.start));
                r = new Info(r.id, l.start, r.end);
            } else {
                if (l.id < r.id) {
                    if (l.end <= r.end) {
                        addToInfoList(ans, l);
                        r = new Info(r.id, l.end, r.end);
                        l = null;
                    } else {
                        addToInfoList(ans, new Info(l.id, l.start, r.end));
                        l = new Info(l.id, r.end, l.end);
                        r = null;
                    }
                } else {
                    if (r.end <= l.end) {
                        addToInfoList(ans, r);
                        l = new Info(l.id, r.end, l.end);
                        r = null;
                    } else {
                        addToInfoList(ans, new Info(r.id, r.start, l.end));
                        r = new Info(r.id, l.end, r.end);
                        l = null;
                    }
                }
            }
            
            
        }
        if ( l != null) {
            addToInfoList(ans, l);
        }
        while (lit.hasNext()) {
            addToInfoList(ans, lit.next());
        }
        
        if ( r != null) {
            addToInfoList(ans, r);
        }
        while (rit.hasNext()) {
            addToInfoList(ans, rit.next());
        }
        return ans;
    }
    
    
    private void addToInfoList(List<Info> ans, Info next) {
        if (ans.isEmpty() || ans.get(ans.size()-1).id != next.id) {
            ans.add(next);
        } else {
            int lastId = ans.size() - 1;
            Info last = ans.get(lastId);
            ans.set(lastId, new Info(next.id, last.start, next.end));
        }
    }

    private static class Info {
        private final int id;
        private final int start;
        private final int end;
        
        public Info(int id, int start, int end) {
            this.id = id;
            this.start = start;
            this.end = end;
        }
    }
}
