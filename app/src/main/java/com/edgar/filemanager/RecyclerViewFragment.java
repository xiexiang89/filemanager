package com.edgar.filemanager;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewFragment extends Fragment {

    static final int INTERNAL_EMPTY_ID = 0x00ff0001;

    final private Handler mHandler = new Handler();

    final private Runnable mRequestFocus = new Runnable() {
        @Override
        public void run() {
            mRecyclerView.focusableViewAvailable(mRecyclerView);
        }
    };

    final private RecyclerView.AdapterDataObserver mObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
        }
    };

    RecyclerView mRecyclerView;
    RecyclerView.Adapter<? extends RecyclerView.ViewHolder> mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    View mEmptyView;
    TextView mStandardEmptyView;
    View mProgressView;
    View mListContainer;
    CharSequence mEmptyText;
    boolean mListShown;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final Context context = getContext();

        FrameLayout root = new FrameLayout(context);

        // ------------------------------------------------------------------

        root.addView(obtainProcessView(context), new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // ------------------------------------------------------------------

        FrameLayout lframe = new FrameLayout(context);
        lframe.setId(R.id.list_container);

        lframe.addView(obtainEmptyView(context), new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        RecyclerView lv = new RecyclerView(context);
        lv.setId(android.R.id.list);
        lframe.addView(lv, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        root.addView(lframe, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // ------------------------------------------------------------------

        root.setLayoutParams(new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ensureList();
    }

    /**
     * The default content for a ListFragment has a TextView that can
     * be shown when the list is empty.  If you would like to have it
     * shown, call this method to supply the text it should use.
     */
    public void setEmptyText(CharSequence text) {
        ensureList();
        if (mStandardEmptyView == null) {
            throw new IllegalStateException("Can't be used with a custom content view");
        }
        if (mEmptyView != null) {
            TextView textView = mEmptyView.findViewById(R.id.empty_text);
            textView.setText(text);
        } else {
            mStandardEmptyView.setText(text);
        }
        mEmptyText = text;
    }

    private View obtainEmptyView(Context context) {
        if (mEmptyView == null) {
            mEmptyView = onCreateEmptyView(context);
        }
        return mEmptyView;
    }

    protected View onCreateEmptyView(Context context) {
        return createDefaultEmptyView(context);
    }

    protected final View createDefaultEmptyView(Context context) {
        TextView tv = new TextView(context);
        tv.setId(R.id.empty);
        tv.setGravity(Gravity.CENTER);
        return tv;
    }

    public void setEmptyView(View emptyView) {
        mEmptyView = emptyView;
    }

    private View obtainProcessView(Context context) {
        if (mProgressView == null) {
            mProgressView = onCreateProgressView(context);
        }
        return mProgressView;
    }

    protected View onCreateProgressView(Context context) {
        return createDefaultProgressView(context);
    }

    protected final View createDefaultProgressView(Context context) {
        LinearLayout pframe = new LinearLayout(context);
        pframe.setId(R.id.progress_view);
        pframe.setOrientation(LinearLayout.VERTICAL);
        pframe.setVisibility(View.GONE);
        pframe.setGravity(Gravity.CENTER);

        ProgressBar progress = new ProgressBar(context, null,
                android.R.attr.progressBarStyleLarge);
        pframe.addView(progress, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return pframe;
    }

    public void setProgressView(View progressView) {
        mProgressView = progressView;
    }

    public RecyclerView getRecyclerView() {
        ensureList();
        return mRecyclerView;
    }

    /**
     * Control whether the list is being displayed.  You can make it not
     * displayed if you are waiting for the initial data to show in it.  During
     * this time an indeterminant progress indicator will be shown instead.
     *
     * <p>Applications do not normally need to use this themselves.  The default
     * behavior of ListFragment is to start with the list not being shown, only
     * showing it once an adapter is given with {@link #setAdapter(RecyclerView.Adapter)}.
     * If the list at that point had not been shown, when it does get shown
     * it will be do without the user ever seeing the hidden state.
     *
     * @param shown If true, the list view is shown; if false, the progress
     * indicator.  The initial value is true.
     */
    public void setListShown(boolean shown) {
        setListShown(shown, true);
    }

    /**
     * Like {@link #setListShown(boolean)}, but no animation is used when
     * transitioning from the previous state.
     */
    public void setListShownNoAnimation(boolean shown) {
        setListShown(shown, false);
    }

    private void updateEmptyState() {
        if (mAdapter.getItemCount() > 0) {
            mEmptyView.setVisibility(View.VISIBLE);
        } else {
            mEmptyView.setVisibility(View.GONE);
        }
    }

    /**
     * Control whether the list is being displayed.  You can make it not
     * displayed if you are waiting for the initial data to show in it.  During
     * this time an indeterminant progress indicator will be shown instead.
     *
     * @param shown If true, the list view is shown; if false, the progress
     * indicator.  The initial value is true.
     * @param animate If true, an animation will be used to transition to the
     * new state.
     */
    private void setListShown(boolean shown, boolean animate) {
        ensureList();
        if (mProgressView == null) {
            throw new IllegalStateException("Can't be used with a custom content view");
        }
        if (mListShown == shown) {
            return;
        }
        mListShown = shown;
        if (shown) {
            if (animate) {
                mProgressView.startAnimation(AnimationUtils.loadAnimation(
                        getContext(), android.R.anim.fade_out));
                mListContainer.startAnimation(AnimationUtils.loadAnimation(
                        getContext(), android.R.anim.fade_in));
            } else {
                mProgressView.clearAnimation();
                mListContainer.clearAnimation();
            }
            mProgressView.setVisibility(View.GONE);
            mListContainer.setVisibility(View.VISIBLE);
        } else {
            if (animate) {
                mProgressView.startAnimation(AnimationUtils.loadAnimation(
                        getContext(), android.R.anim.fade_in));
                mListContainer.startAnimation(AnimationUtils.loadAnimation(
                        getContext(), android.R.anim.fade_out));
            } else {
                mProgressView.clearAnimation();
                mListContainer.clearAnimation();
            }
            mProgressView.setVisibility(View.VISIBLE);
            mListContainer.setVisibility(View.GONE);
        }
    }

    private void ensureList() {
        if (mRecyclerView != null) {
            return;
        }
        View root = getView();
        if (root == null) {
            throw new IllegalStateException("Content view not yet created");
        }
        if (root instanceof RecyclerView) {
            mRecyclerView = (RecyclerView) root;
        } else {
            mStandardEmptyView = root.findViewById(INTERNAL_EMPTY_ID);
            if (mStandardEmptyView == null) {
                mEmptyView = root.findViewById(R.id.empty);
            } else {
                mEmptyView = mStandardEmptyView;
                mStandardEmptyView.setVisibility(View.GONE);
            }
            mProgressView = root.findViewById(R.id.progress_view);
            mListContainer = root.findViewById(R.id.list_container);
            View rawListView = root.findViewById(android.R.id.list);
            if (!(rawListView instanceof RecyclerView)) {
                if (rawListView == null) {
                    throw new RuntimeException(
                            "Your content must have a ListView whose id attribute is " +
                                    "'android.R.id.list'");
                }
                throw new RuntimeException(
                        "Content has view with id attribute 'android.R.id.list' "
                                + "that is not a ListView class");
            }
            mRecyclerView = (RecyclerView) rawListView;
        }
        mListShown = true;
        mRecyclerView.setLayoutManager(obtainLayoutManager());
        if (mAdapter != null) {
            RecyclerView.Adapter<?> adapter = mAdapter;
            mAdapter = null;
            setAdapter(adapter);
        } else {
            // We are starting without an adapter, so assume we won't
            // have our data right away and start with the progress indicator.
            if (mProgressView != null) {
                setListShown(false, false);
            }
        }
        mHandler.post(mRequestFocus);
    }

    public void setAdapter(RecyclerView.Adapter<? extends RecyclerView.ViewHolder> adapter) {
        boolean hadAdapter = mAdapter != null;
        mAdapter = adapter;
        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(adapter);
            if (hadAdapter) {
                mAdapter.registerAdapterDataObserver(mObserver);
            }
            if (!mListShown && !hadAdapter) {
                // The list was hidden, and previously didn't have an
                // adapter.  It is now time to show it.
                setListShown(true, getView().getWindowToken() != null);
            }
        }
    }

    private RecyclerView.LayoutManager obtainLayoutManager() {
        if (mLayoutManager == null) {
            mLayoutManager = new LinearLayoutManager(getContext());
        }
        return mLayoutManager;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        mLayoutManager = layoutManager;
        if (mRecyclerView != null) {
            mRecyclerView.setLayoutManager(mLayoutManager);
        }
    }
}